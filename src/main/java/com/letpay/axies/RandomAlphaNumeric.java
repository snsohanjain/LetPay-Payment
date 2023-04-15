package com.letpay.axies;

import java.security.SecureRandom;

public class RandomAlphaNumeric {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static void main(String[] args) {
        String randomString = generateRandomString(6);
        System.out.println(randomString);
    }
    
    public static String generateRandomString(int length) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = rnd.nextInt(ALPHA_NUMERIC_STRING.length());
            sb.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return sb.toString();
    }
    public static long generateRandomNumeric(int length) {
        SecureRandom rnd = new SecureRandom();
        long minValue = (long) Math.pow(10, length - 1); // minimum value for the random number
        long maxValue = (long) Math.pow(10, length) - 1; // maximum value for the random number
        long randomNumber = Math.abs(minValue + rnd.nextLong() % (maxValue - minValue + 1));
        return randomNumber;
    }

}
