package com.lbaburic.learning.cardcreator.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OibUtilsTest {

    @Test
    void test_isValidOib_true() {

        assertTrue(OibUtils.isValidOib("50640346679"));

    }

    @Test
    void test_isValidOib_false() {
        assertFalse(OibUtils.isValidOib("50640346671"));
    }
}