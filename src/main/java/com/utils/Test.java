package com.utils;

import java.util.Random;

class Test {}

class CryptoWalletSimulator {

    private static final String HEX_CHARS = "0123456789abcdef";

    public static String generateAddress() {
        byte[] addressBytes = new byte[20];
        new Random().nextBytes(addressBytes);
        return Hex.encode(addressBytes);
    }

    public static void main(String[] args) {
        String address = generateAddress();
        System.out.println("Address: " + address);
    }
}

class Hex {

    private static final char[] HEX_CHARS = "0123456789abcdef".toCharArray();

    public static String encode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX_CHARS[(b & 0xf0) >>> 4]);
            sb.append(HEX_CHARS[b & 0x0f]);
        }
        return sb.toString();
    }

    public static byte[] decode(String hexString) {
        byte[] bytes = new byte[hexString.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] =
                    (byte)
                            ((Character.digit(hexString.charAt(i * 2), 16) << 4)
                                    + Character.digit(hexString.charAt(i * 2 + 1), 16));
        }
        return bytes;
    }
}
