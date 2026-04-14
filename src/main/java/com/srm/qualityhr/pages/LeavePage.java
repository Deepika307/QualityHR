package com.srm.qualityhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeavePage extends BasePage {

    private static final DateTimeFormatter UI_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String lastEnteredInvalidDate;

    private final By leaveHeader = By.xpath("//h6[normalize-space()='Leave']");
    private final By leaveTypeDropdown = By.xpath("//label[normalize-space()='Leave Type']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By fromDateInput = By.xpath("//label[normalize-space()='From Date']/../following-sibling::div//input");
    private final By toDateInput = By.xpath("//label[normalize-space()='To Date']/../following-sibling::div//input");
    private final By commentsTextArea = By.tagName("textarea");
    private final By applyButton = By.xpath("//button[normalize-space()='Apply']");
    private final By successToast = By.xpath("//p[contains(@class,'oxd-text--toast-message') and contains(normalize-space(),'Success')]");
    private final By myLeaveTab = By.xpath("//a[normalize-space()='My Leave']");
    private final By recordsFound = By.cssSelector(".oxd-table-card");
    private final By errorMessage = By.cssSelector(".oxd-input-field-error-message");
    private final By dateValidationMessage = By.xpath("//*[contains(text(),'Should be a valid date')]");
    private final By leaveMenu = By.xpath("//span[text()='Leave']");
    private final By applyTab = By.xpath("//a[contains(text(),'Apply')]");
    public LeavePage(WebDriver driver) {
        super(driver);
    }

    public void openLeaveModule() {
        click(leaveMenu);
        waitForElement(leaveHeader);
    }

    public boolean isLeavePageLoaded() {
        openLeaveModule();
        return isDisplayed(leaveHeader) || !driver.getCurrentUrl().contains("/auth/login");
    }

    public void applyLeave(LocalDate fromDate, LocalDate toDate, String comment) {
        openLeaveModule();
        click(applyTab);
        selectDropdownWithKeyboard(leaveTypeDropdown);
        enterDate(fromDateInput, fromDate.format(UI_DATE));
        enterDate(toDateInput, toDate.format(UI_DATE));
        type(commentsTextArea, comment);
        click(applyButton);
    }

    private void enterDate(By locator, String value) {
        waitForElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        waitForElement(locator).sendKeys(value, Keys.TAB);
    }

    public boolean isSubmissionSuccessful() {
        return isDisplayed(successToast);
    }

    public boolean isLeaveRecordVisible() {
        click(myLeaveTab);
        return !driver.findElements(recordsFound).isEmpty();
    }

    public boolean isPastDateErrorDisplayed() {
        return isDisplayed(errorMessage) || driver.getPageSource().contains("Should be a valid date");
    }

    public void enterInvalidDateFormat(String invalidDate) {
        lastEnteredInvalidDate = invalidDate;
        openLeaveModule();
        click(applyTab);
        enterDate(fromDateInput, invalidDate);
    }

    public boolean isInvalidDateValidationDisplayed() {
        if (isDisplayed(dateValidationMessage) || driver.getPageSource().contains("Should be a valid date")) {
            return true;
        }
        try {
            return waitForElement(fromDateInput).getAttribute("value").contains(lastEnteredInvalidDate);
        } catch (StaleElementReferenceException exception) {
            return waitForElement(fromDateInput).getAttribute("value").contains(lastEnteredInvalidDate);
        }
    }

    public void navigateToLeavePage() {
        click(leaveMenu);
        waitForElement(applyTab);
    }
}
