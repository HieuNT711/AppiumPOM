package com.utils;

import java.math.BigDecimal;

public class CalculateUtils {
    public static double roundTo2DecimalPlaces(String numberString) {
        try {
            float number = Float.parseFloat(numberString);
            return Math.round(number * 100) / 100;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static BigDecimal roundToNumber(double a, double b) {
        BigDecimal bigDecimal = new BigDecimal(a / (round(b, 6)));
        BigDecimal roundedDecimal = bigDecimal.setScale(6, BigDecimal.ROUND_DOWN);
        return roundedDecimal;
    }
}
