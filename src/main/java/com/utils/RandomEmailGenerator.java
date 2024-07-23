package com.utils;

import java.util.Random;

public class RandomEmailGenerator {
    private static final String DOMAIN = "example.com";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomEmail() {
        String emailAddress = "";
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            emailAddress += ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        emailAddress += TimeUtils.getCurrentTimeStamp() + "@" + DOMAIN;
        return emailAddress;
    }
}
