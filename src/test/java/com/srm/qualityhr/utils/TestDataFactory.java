package com.srm.qualityhr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TestDataFactory {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("ddMMyyHHmmss");

    private TestDataFactory() {
    }

    public static String uniqueSuffix() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
