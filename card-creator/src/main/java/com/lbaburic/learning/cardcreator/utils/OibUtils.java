package com.lbaburic.learning.cardcreator.utils;

public final class OibUtils {

    private OibUtils() {
    }

    public static boolean isValidOib(String oib) {
        char[] chars = oib.toCharArray();

        int a = 10;
        for (int i = 0; i < 10; i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                return false;
            }
            a = a + (c - '0');
            a = a % 10;
            if (a == 0) {
                a = 10;
            }
            a *= 2;
            a = a % 11;
        }
        int check = 11 - a;
        check = check % 10;

        if (check != (chars[10] - '0')) {
            return false;
        }
        return true;
    }
}
