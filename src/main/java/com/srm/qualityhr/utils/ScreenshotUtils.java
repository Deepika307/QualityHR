package com.srm.qualityhr.utils;

import com.srm.qualityhr.constants.FrameworkConstants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    private ScreenshotUtils() {
    }

    public static String capture(WebDriver driver, String testName) {
        try {
            Files.createDirectories(FrameworkConstants.SCREENSHOT_DIR);
            String fileName = testName + "_" + LocalDateTime.now().format(FORMATTER) + ".png";
            Path destination = FrameworkConstants.SCREENSHOT_DIR.resolve(fileName);
            Path source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            return destination.toAbsolutePath().toString();
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to capture screenshot", exception);
        }
    }
}
