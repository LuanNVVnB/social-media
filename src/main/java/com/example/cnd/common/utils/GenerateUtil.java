package com.example.cnd.common.utils;

import java.security.SecureRandom;

/**
 * File: com.example.cnd.common.utils
 * Description:  <div style="font-weight: bold; color: #0073e6;"> GenerateUtil </div><hr>
 * Author:  <span style="font-weight: bold; color: #00a65a;"></span>
 * Date: <span style="font-weight: bold; color: #ff5722;">12/9/2023</span>
 **/
public class GenerateUtil {
    /**
     * Generate random password
     *
     * @param length, password refresh
     * @return OTP
     * @exam input length = 3, character = "abedAbed123c" => a13, aB2,Bc3 ...
     */
    public static String randomWithCharacter(Integer length, String characters) {
        int characterLength = characters.length();
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characterLength);
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    /**
     * Generate random Number1-Letter-Number2
     *
     * @param numberLength          length random of Number1 If it is 1 digit, fill in the beginning with 0.
     * @param letterLength          number of characters into letter
     * @param characterLetter       character random into letter
     * @param characterNumberLength length random of Number2
     * @param characterNumber       input character into Number2
     * @exam 01A000000001
     */
    public static String generateRandomNumberAndLetter(int numberLength, int letterLength, String characterLetter,
            int characterNumberLength, long characterNumber) {
        // generate Number1
        String randomNum = generateRandomNumberWithLength(numberLength);
        //generate Letter
        String randomLetter = randomWithCharacter(letterLength, characterLetter);
        // generate Number2
        String formattedCustomerNumber = String.format("%0" + characterNumberLength + "d", characterNumber);
        return String.format("%s%s%s", randomNum, randomLetter, formattedCustomerNumber);
    }

    /**
     * Generate random character with number
     * If it is less than n digits, fill in the beginning with 0.
     *
     * @param firstCharacter the first character
     * @param numberLength   length number random
     * @exam 400000001
     */
    public static long generateServiceUsageNumber(String firstCharacter, int numberLength) {
        SecureRandom random = new SecureRandom();
        // Generate a random n-digit number
        int maxValue = (int) Math.pow(10, numberLength) - 1;
        int randomNum = random.nextInt(maxValue);
        // Format the number to ensure it has n digits with leading zeros if necessary
        String formattedNumber = String.format("%0" + numberLength + "d", randomNum);
        // Combine the components to create the service usage number
        String serviceUsageNumber = firstCharacter + formattedNumber;
        return Long.parseLong(serviceUsageNumber);
    }

    /**
     * Generate random Number With length
     * If it is 1 digit, fill in the beginning with 0.
     *
     * @exam 01, 11, 100,102
     */
    public static String generateRandomNumberWithLength(int numberLength) {
        int maxValue = (int) Math.pow(10, numberLength) - 1;
        SecureRandom random = new SecureRandom();
        // Generate a random number between 0 and number length max (inclusive)
        int randomNumber = random.nextInt(maxValue);
        // Format the number to ensure it has 2 digits with a leading zero if necessary
        return String.format("%0" + numberLength + "d", randomNumber);
    }
}
