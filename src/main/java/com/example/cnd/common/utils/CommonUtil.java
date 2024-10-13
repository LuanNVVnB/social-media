package com.example.cnd.common.utils;

import com.example.cnd.common.aop.Alias;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;

import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> CommonUtil </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
@Slf4j
public class CommonUtil {
    /**
     * Logs the execution time of a process.
     *
     * @param startTime The start time of the process in milliseconds.
     */
    public static void logMeasurementEndTime(long startTime) {
        // Get the current time as the end time
        var endTime = System.currentTimeMillis();

        // Calculate the execution time in milliseconds
        long executionTime = endTime - startTime;

        // Log the execution time
        log.info("{} executes in {} ms", "JwtRequestFilter", executionTime);
    }

    /**
     * Parses an object to its string representation.
     *
     * @param obj The object to be parsed to a string.
     * @return The string representation of the object, or null if the object is null or cannot be parsed.
     */
    public static String parseObjectToString(Object obj) {
        // Check if the object is null
        if (Objects.isNull(obj)) {
            return null;
        }

        // Check if the object is primitive, a Number, or a String
        if (obj.getClass()
                .isPrimitive() || obj instanceof Number || obj instanceof String) {
            return String.valueOf(obj);
        }

        // Try to serialize the object to a JSON string using ObjectMapper
        try {
            return JsonUtil.getObjectMapper()
                    .writeValueAsString(obj);
        } catch (Exception ignored) {
            // Handle exceptions, or ignore if serialization fails
        }

        // Return null if the object cannot be parsed
        return null;
    }

    /**
     * Checks if a Collection is null or empty.
     *
     * @param c The Collection to be checked.
     * @return True if the Collection is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(final Collection<?> c) {
        // Check if the Collection c is either null or is empty
        return c == null || c.isEmpty();
    }

    /**
     * Checks if a Map is null or empty.
     *
     * @param m The Map to be checked.
     * @return True if the Map is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(final Map<?, ?> m) {
        // Check if the Map m is either null or is empty
        return null == m || m.isEmpty();
    }

    /**
     * Checks if an array of objects is null or empty.
     *
     * @param obj The array of objects to be checked.
     * @return True if the array is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(Object[] obj) {
        // Check if the array obj is either null or has zero length
        return Objects.isNull(obj) || obj.length == 0;
    }

    /**
     * Checks if a String is null or empty.
     *
     * @param sourceString The String to be checked.
     * @return True if the String is null or empty, false otherwise.
     */
    public static boolean isNullOrEmpty(String sourceString) {
        return sourceString == null || sourceString.equals("");
    }

    /**
     * Checks if a String is null or contains only white spaces.
     *
     * @param sourceString The String to be checked.
     * @return True if the String is null or contains only white spaces, false otherwise.
     */
    public static boolean isNullOrWhiteSpace(String sourceString) {
        return sourceString == null || sourceString.isBlank();
    }

    /**
     * Converts an object to its Long representation.
     *
     * @param ob The object to be converted to a Long.
     * @return The Long representation of the object, or null if the object is null or cannot be parsed to a Long.
     * @throws NumberFormatException If the string representation of the object cannot be parsed to a Long.
     */
    public static Long convertObjectToLong(Object ob) throws NumberFormatException {
        Long value = null;
        if (ob != null) {
            value = Long.parseLong(ob.toString());
        }
        return value;
    }

    /**
     * Converts an object to its Integer representation.
     *
     * @param ob The object to be converted to an Integer.
     * @return The Integer representation of the object, or null if the object is null or cannot be parsed to an Integer.
     * @throws NumberFormatException If the string representation of the object cannot be parsed to an Integer.
     */
    public static Integer convertObjectToInteger(Object ob) throws NumberFormatException {
        Integer value = null;
        if (ob != null) {
            value = Integer.parseInt(ob.toString());
        }
        return value;
    }

    /**
     * Converts an object to its string representation.
     *
     * @param ob The object to be converted to a string.
     * @return The string representation of the object, or an empty string if the object is null.
     */
    public static String convertObjectToString(Object ob) {
        String value = "";
        if (ob != null) {
            value = String.valueOf(ob);
        }
        return value;
    }

    /**
     * Converts an object to a LocalDateTime.
     *
     * @param ob The object to be converted to a LocalDateTime.
     * @return The LocalDateTime value obtained from the string representation of the object, or null if the object is null or empty.
     * @throws DateTimeParseException If the string representation cannot be parsed into a LocalDateTime.
     */
    public static LocalDateTime convertObjectToLocalDateTime(Object ob) throws DateTimeParseException {
        LocalDateTime value = null;
        String obStr = convertObjectToString(ob);
        if (!CommonUtil.isNullOrWhiteSpace(obStr)) {
            Timestamp timestamp = Timestamp.valueOf(obStr);
            value = timestamp.toLocalDateTime();
        }
        return value;
    }

    /**
     * Converts an object to a LocalDate.
     *
     * @param ob The object to be converted to a LocalDate.
     * @return The LocalDate value parsed from the string representation of the object, or null if the object is null or empty.
     * @throws DateTimeParseException If the string representation cannot be parsed into a LocalDate.
     */
    public static LocalDate convertObjectToLocalDate(Object ob) throws DateTimeParseException {
        LocalDate value = null;
        String obStr = convertObjectToString(ob);
        if (!CommonUtil.isNullOrWhiteSpace(obStr)) {
            value = LocalDate.parse(obStr);
        }
        return value;
    }

    /**
     * Finds a field in a class based on either the field name or the name specified in the @Alias annotation.
     *
     * @param clazz      The class to search for the field.
     * @param columnName The name of the column or field to search for.
     * @param <T>        The type of the class.
     * @return The Field object found, or null if no matching field is found.
     */
    public static <T> Field findFieldByFieldNameOrColumnNameAnnotation(Class<T> clazz, String columnName) {
        Field foundField = null;

        // Get all declared fields of the class
        final Field[] fields = clazz.getDeclaredFields();

        // Iterate through each field
        for (Field field : fields) {
            // Get the name of the field
            final String fieldName = field.getName();

            // Check if the field has an @Alias annotation
            final Alias column = field.getAnnotation(Alias.class);

            // Find by column name primarily
            // If @Alias is not specified, we will find by field name
            if (column != null) {
                // Get the mapping column name specified in the @Alias annotation
                final String mappingColumnName = column.name();

                // Check if the mapping column name matches the provided column name
                if (mappingColumnName.equalsIgnoreCase(columnName)) {
                    // If there is a match, set the foundField and exit the loop
                    foundField = field;
                    break;
                }
            } else if (fieldName.equalsIgnoreCase(columnName)) {
                // If no @Alias annotation is present, check if the field name matches the provided column name
                // If there is a match, set the foundField and exit the loop
                foundField = field;
                break;
            }
        }

        // Return the foundField (may be null if no matching field is found)
        return foundField;
    }

    /**
     * Extracts the host name from the HttpHeaders.
     *
     * @param httpHeaders The HttpHeaders from which to extract the host name.
     * @return The host name extracted from the HttpHeaders, or an empty string if the host information is not present.
     */
    public static String getHostName(HttpHeaders httpHeaders) {
        InetSocketAddress host = httpHeaders.getHost();
        if (host != null) {
            return host.getHostName();
        }
        return "";
    }

    /**
     * Converts a delimited string into a List of strings.
     *
     * @param str       The input string to be converted.
     * @param delimiter The delimiter used to split the input string.
     * @return A List of strings obtained by splitting the input string using the specified delimiter.
     * An empty List is returned if the input string is null or empty.
     */
    public static List<String> convertFromStringToList(String str, String delimiter) {
        List<String> resultList = new ArrayList<>();
        if (!isNullOrWhiteSpace(str)) {
            resultList = Arrays.asList(str.split(delimiter));
        }
        return resultList;
    }

    /**
     * Extracts the host from the Origin header of an HTTP request.
     *
     * @param request The HttpServletRequest from which to extract the host.
     * @return The host extracted from the Origin header, or an empty string if the Origin header is not present or empty.
     * @throws URISyntaxException If there is an issue parsing the URI from the Origin header.
     */
    public static String getHostFromHTTPRequest(HttpServletRequest request) throws URISyntaxException {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        if (isNullOrWhiteSpace(origin)) {
            return "";
        }
        URI uri = new URI(origin);
        return uri.getHost();
    }

    /**
     * Checks if the given string matches the specified regular expression pattern.
     *
     * @param str          The input string to be checked.
     * @param regexPattern The regular expression pattern to match against.
     * @return True if the string matches the regex pattern, false otherwise.
     */
    public static boolean isStringMatchedWithRegex(String str, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        return pattern.matcher(str)
                .matches();
    }

    /**
     * Formats the given OTP (One-Time Password) as a 6-digit string.
     *
     * @param otp The OTP to be formatted.
     * @return A formatted string representing the OTP, or null if the input OTP is null.
     */
    public static String formatOTP(Integer otp) {
        String authCd = null;
        if (otp == null) {
            return authCd;
        }
        authCd = String.format("%06d", otp);
        return authCd;
    }
}