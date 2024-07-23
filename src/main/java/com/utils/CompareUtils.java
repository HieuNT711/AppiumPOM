package com.utils;

public class CompareUtils {
    public static boolean compareFloat(float x, float y) {
        float epsilon = 1.01f;
        if ((x - y) < epsilon) return true;
        return false;
    }
}
