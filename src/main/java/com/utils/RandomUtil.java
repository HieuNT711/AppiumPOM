package com.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {

    public static String getRandomSpecialString(int n) {
        String AlphaNumericString = "~!@#$%^&*()_+><:\"[]" + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    public static int getRandomNumber(int max) {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, max);
        return randomNumber;
    }

    public static String getUniqueString(String text) {
        String timeStamp = String.valueOf(TimeUtils.getCurrentTimeStamp());
        return text + timeStamp; // timeStamp.substring(timeStamp.length()-6);
    }
}
