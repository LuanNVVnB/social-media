package com.example.cnd.common.utils;

public class CommonUtil {
    public static boolean isNullOrWhiteSpace(String str) {
        return str == null || str.trim()
                .isEmpty();
    }
}
