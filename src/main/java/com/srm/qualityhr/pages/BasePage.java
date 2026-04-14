package com.srm.qualityhr.pages;

import com.srm.qualityhr.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForElement(By locator) {
        return WaitUtils.waitForVisible(driver, locator);
    }

    protected WebElement waitForClickable(By locator) {
        return WaitUtils.waitForClickable(driver, locator);
    }

    protected void click(By locator) {
        try {
            waitForClickable(locator).click();
        } catch (StaleElementReferenceException exception) {
            waitForClickable(locator).click();
        }
    }

    protected void type(By locator, String value) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(By locator) {
        return waitForElement(locator).getText().trim();
    }

    protected boolean isDisplayed(By locator) {
        return !driver.findElements(locator).isEmpty() && driver.findElement(locator).isDisplayed();
    }

    protected void waitForUrlContains(String text) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains(text));
    }

    protected List<WebElement> findAll(By locator) {
        waitForElement(locator);
        return driver.findElements(locator);
    }

    protected void jsClick(By locator) {
        WebElement element = waitForClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void pressEnter(By locator) {
        waitForElement(locator).sendKeys(Keys.ENTER);
    }

    protected void selectDropdownOption(By trigger, String optionText) {
        jsClick(trigger);
        List<WebElement> options = getDropdownOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(optionText)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
                return;
            }
        }
        if (!options.isEmpty()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
        } else {
            throw new IllegalStateException("No dropdown options were available to select");
        }
    }

    protected void selectFirstDropdownOption(By trigger) {
        jsClick(trigger);
        List<WebElement> options = getDropdownOptions();
        if (options.isEmpty()) {
            throw new IllegalStateException("No dropdown options were available to select");
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", options.get(0));
    }

    protected void selectDropdownWithKeyboard(By trigger) {
        WebElement element = waitForClickable(trigger);
        element.click();
        element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    private List<WebElement> getDropdownOptions() {
        By primaryOptions = By.cssSelector("div[role='option']");
        By fallbackOptions = By.xpath("//div[@role='listbox']//*[self::span or self::div][normalize-space()]");

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(drv ->
                !drv.findElements(primaryOptions).isEmpty() || !drv.findElements(fallbackOptions).isEmpty());

        List<WebElement> options = new ArrayList<>(driver.findElements(primaryOptions));
        if (options.isEmpty()) {
            options = new ArrayList<>(driver.findElements(fallbackOptions));
        }
        options.removeIf(option -> !option.isDisplayed() || option.getText().trim().isEmpty());
        return options;
    }

    protected boolean isAnyDisplayed(By... locators) {
        for (By locator : locators) {
            if (isDisplayed(locator)) {
                return true;
            }
        }
        return false;
    }
}
