package com.srm.qualityhr.constants;

import java.nio.file.Path;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String CONFIG_FILE = "config.properties";
    public static final Path SCREENSHOT_DIR = Path.of("screenshots");
    public static final Path REPORT_DIR = Path.of("reports");
    public static final String REPORT_NAME = "extent-report.html";
}
