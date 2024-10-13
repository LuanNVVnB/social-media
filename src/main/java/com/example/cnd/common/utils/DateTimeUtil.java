package com.example.cnd.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.GenericValidator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> DateTimeUtil </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
@Slf4j
public class DateTimeUtil {
    /**
     * Validates and converts a string to LocalDateTime while ignoring microsecond patterns.
     */
    public static LocalDateTime validateAndConvertLocalDateTimeWithMicro(String dateStr, String pattern) {
        // No need to validate pattern because GenericValidator.isDate cannot validate patterns with microseconds
        return convertFromStringToLocalDateTimeWithPattern(dateStr, pattern, false);
    }

    /**
     * Validates and converts a string to LocalDateTime.
     */
    public static LocalDateTime validateAndConvertLocalDateTime(String dateStr, String pattern) {
        return convertFromStringToLocalDateTimeWithPattern(dateStr, pattern, true);
    }

    /**
     * Validates and converts a string to LocalDate.
     */
    public static LocalDate validateAndConvertLocalDate(String dateStr, String pattern) {
        return convertFromStringToLocalDateWithPattern(dateStr, pattern);
    }

    /**
     * Checks if a string matches the given date format pattern.
     */
    public static boolean isStringMatchedDateFormat(String dateStr, String pattern) {
        return GenericValidator.isDate(dateStr, pattern, true);
    }

    /**
     * Parses a string to LocalDateTime using the specified pattern.
     *
     * @param input   the input string
     * @param pattern the date/time pattern
     * @return LocalDateTime object or null if parsing fails
     */
    public static LocalDateTime convertFromStringToLocalDateTimeWithPattern(String input, String pattern,
            boolean validatePattern) {
        if (CommonUtil.isNullOrWhiteSpace(input) || (validatePattern && !isStringMatchedDateFormat(input, pattern))) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * Parses a string to LocalDate using the specified pattern.
     *
     * @param input   the input string
     * @param pattern the date/time pattern
     * @return LocalDate object or null if parsing fails
     */
    public static LocalDate convertFromStringToLocalDateWithPattern(String input, String pattern) {
        if (CommonUtil.isNullOrWhiteSpace(input) || !isStringMatchedDateFormat(input, pattern)) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * Converts LocalDateTime to a string using the specified pattern.
     *
     * @param date    the LocalDateTime object
     * @param pattern the date/time pattern
     * @return formatted string or empty string if input is null
     */
    public static String convertFromDateToStringWithPattern(LocalDateTime date, String pattern) {
        if (date == null) {
            return "";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return date.format(formatter);
        } catch (DateTimeException ex) {
            return "";
        }
    }

    /**
     * Converts LocalDate to a string using the specified pattern.
     *
     * @param date    the LocalDate object
     * @param pattern the date/time pattern
     * @return formatted string or empty string if input is null
     */
    public static String convertFromDateToStringWithPattern(LocalDate date, String pattern) {
        if (date == null) {
            return "";
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return date.format(formatter);
        } catch (DateTimeException ex) {
            return "";
        }
    }

    /**
     * Checks if a date is within a specified range of months.
     *
     * @param from             start date
     * @param to               end date
     * @param acceptedInterval number of months
     * @return true if 'from' is not before 'to' minus acceptedInterval
     */
    public static boolean isDayInMonthRange(LocalDateTime from, LocalDateTime to, int acceptedInterval) {
        LocalDateTime priorMonthDay = to.minusMonths(acceptedInterval);
        LocalDateTime priorMonthDayWithZeroHour = LocalDateTime.of(priorMonthDay.getYear(), priorMonthDay.getMonth(),
                priorMonthDay.getDayOfMonth(), 0, 0, 0);
        return !from.isBefore(priorMonthDayWithZeroHour);
    }

    /**
     * Compares two LocalDateTime objects.
     *
     * @param from         start date
     * @param to           end date
     * @param isCheckEqual whether to check equality
     * @return true if 'from' is before or equal to 'to'
     */
    public static boolean isLocalDateTimeBefore(LocalDateTime from, LocalDateTime to, Boolean isCheckEqual) {
        if (isCheckEqual) {
            return from.isBefore(to) || from.isEqual(to);
        }
        return from.isBefore(to);
    }

    /**
     * Compares two LocalDate objects.
     *
     * @param from         start date
     * @param to           end date
     * @param isCheckEqual whether to check equality
     * @return true if 'from' is before or equal to 'to'
     */
    public static boolean isLocalDateBefore(LocalDate from, LocalDate to, Boolean isCheckEqual) {
        if (isCheckEqual) {
            return from.isBefore(to) || from.isEqual(to);
        }
        return from.isBefore(to);
    }

    /**
     * Converts a Date object to LocalDateTime.
     *
     * @param date the Date object
     * @return LocalDateTime object or null if input is null
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * Converts a LocalDateTime object to Date.
     *
     * @param date the LocalDateTime object
     * @return Date object or null if input is null
     */
    public static Date toDate(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return Date.from(date.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    /**
     * Gets the current LocalDateTime.
     *
     * @return current LocalDateTime
     */
    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }
}