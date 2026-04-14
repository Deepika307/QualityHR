package com.srm.qualityhr.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout")))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout")))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getInt("timeout")))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static WebElement fluentWaitForVisible(WebDriver driver, By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getInt("timeout")))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class)
                .until(drv -> drv.findElement(locator).isDisplayed() ? drv.findElement(locator) : null);
    }
}
