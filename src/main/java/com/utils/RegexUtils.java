package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    private RegexUtils() {
        throw new IllegalStateException("RegexUtils class");
    }

    public static boolean isPatternMatched(String pattern, String check) {
        Pattern patternRegex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternRegex.matcher(check);
        return matcher.find();
    }

    public static String getDataByRegex(
            String regexPattern, String text, int groupRegex, String replacePatternRegex) {
        String data;
        Pattern patternRegex = Pattern.compile(regexPattern);
        Matcher matcher = patternRegex.matcher(text);
        if (matcher.find()) {
            data = matcher.group(groupRegex);
            data = data.replaceAll(replacePatternRegex, "");
            data = data.trim();
        } else
            throw new RuntimeException(
                    String.format(
                            "Cannot extract value with regex %s from text %s", regexPattern, text));
        return data;
    }

    public static void main(String[] args) {
        System.out.println(getDataByRegex("(\\d.\\d)", "5.0(109)", 0, ""));
        System.out.println(getDataByRegex("\\w+\\d.*\\w", "5.0(109)", 0, ""));
    }
}
