package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtils {

    private TimeUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    // yyyy-MM-dd hh:mm:ss timezone: UTC
    public static String getTodayDateWithTimeZone(String dateFormat, String timeZone) {
        long ts = getCurrentTimeStamp();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        df.setTimeZone(TimeZone.getTimeZone(timeZone));
        return df.format(new Date(ts));
    }

    public static String getTodayDate(String dateFormat) {
        long ts = getCurrentTimeStamp();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(new Date(ts));
    }

    public static void main(String[] args) {
        System.out.println(String.format("Newu la a %s", null));
    }
}
