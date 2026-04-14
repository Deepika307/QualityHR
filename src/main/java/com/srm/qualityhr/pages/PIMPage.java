package com.srm.qualityhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage {

    private final By addEmployeeButton = By.xpath("//a[normalize-space()='Add Employee']");
    private final By employeeListButton = By.xpath("//a[normalize-space()='Employee List']");
    private final By firstNameInput = By.name("firstName");
    private final By lastNameInput = By.name("lastName");
    private final By employeeIdInput = By.xpath("(//label[normalize-space()='Employee Id']/../following-sibling::div//input)[1]");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By successToast = By.xpath("//p[contains(@class,'oxd-text--toast-message') and contains(normalize-space(),'Success')]");
    private final By employeeNameSearchInput = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By noRecordsFound = By.xpath("//span[normalize-space()='No Records Found']");
    private final By recordRows = By.cssSelector(".oxd-table-card");
    private final By employeeEditButton = By.cssSelector(".oxd-table-card .bi-pencil-fill, .oxd-table-card i.bi-pencil-fill");
    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By middleNameInput = By.name("middleName");
    private final By requiredMessages = By.cssSelector(".oxd-input-field-error-message");
    private final By dropdownOptions = By.cssSelector("div[role='option']");
    private final By pimMenu = By.xpath("//span[text()='PIM']");

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public void openPimModule() {
        click(pimMenu);
        waitForUrlContains("/pim");
    }

    public String addEmployee(String firstName, String lastName, String employeeId) {
        openPimModule();
        click(addEmployeeButton);
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        waitForElement(employeeIdInput).sendKeys(Keys.chord(Keys.CONTROL, "a"), employeeId);
        click(saveButton);
        waitForElement(personalDetailsHeader);
        return (firstName + " " + lastName).trim();
    }

    public String getDisplayedEmployeeName() {
        String firstName = waitForElement(firstNameInput).getAttribute("value");
        String lastName = waitForElement(lastNameInput).getAttribute("value");
        return (firstName + " " + lastName).trim();
    }

    public void updateMiddleName(String middleName) {
        type(middleNameInput, middleName);
        click(saveButton);
        waitForElement(successToast);
    }

    public String getMiddleName() {
        return waitForElement(middleNameInput).getAttribute("value").trim();
    }

    public boolean searchEmployeeByName(String employeeName) {
        openPimModule();
        click(employeeListButton);
        type(employeeNameSearchInput, employeeName);
        click(searchButton);
        return driver.getPageSource().contains(employeeName)
                || !driver.getPageSource().contains("No Records Found");
    }

    public boolean searchInvalidEmployee(String employeeName) {
        openPimModule();
        click(employeeListButton);
        type(employeeNameSearchInput, employeeName);
        click(searchButton);
        if (isDisplayed(noRecordsFound) || driver.getPageSource().contains("No Records Found")
                || driver.getPageSource().contains("0 Records Found")) {
            return true;
        }
        return !driver.getPageSource().contains(employeeName);
    }

    public void openFirstEmployeeRecord() {
        waitForElement(recordRows);
        click(employeeEditButton);
        waitForElement(personalDetailsHeader);
    }

    public void openAddEmployeeForm() {
        openPimModule();
        click(addEmployeeButton);
        waitForElement(firstNameInput);
    }

    public int submitEmptyEmployeeFormAndGetValidationCount() {
        openAddEmployeeForm();
        click(saveButton);
        return driver.findElements(requiredMessages).size();
    }

    public void navigateToPIM() {
        click(pimMenu);
        waitForElement(addEmployeeButton);
    }

    public boolean hasRequiredFieldValidation() {
        return driver.findElements(requiredMessages).size() > 0
                || driver.getPageSource().contains("Required");
    }

    public int getDropdownOptionCount(By dropdownTrigger) {
        click(dropdownTrigger);
        return driver.findElements(dropdownOptions).size();
    }

    public By getIncludeDropdown() {
        return By.xpath("//label[normalize-space()='Include']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    }
}
