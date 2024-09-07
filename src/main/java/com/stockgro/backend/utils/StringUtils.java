package com.stockgro.backend.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtils {
    public static boolean isStringNullOrEmpty(final String str) {
        return str == null || str.trim().isEmpty();
    }
}
