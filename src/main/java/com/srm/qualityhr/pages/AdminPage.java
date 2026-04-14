package com.srm.qualityhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage {

    private final By adminMenu = By.xpath("//span[normalize-space()='Admin']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By userRoleDropdown = By.xpath("(//label[normalize-space()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text')])[1]");
    private final By employeeNameInput = By.xpath("//label[normalize-space()='Employee Name']/../following-sibling::div//input");
    private final By statusDropdown = By.xpath("//label[normalize-space()='Status']/../following-sibling::div//div[contains(@class,'oxd-select-text')]");
    private final By usernameInput = By.xpath("//label[normalize-space()='Username']/../following-sibling::div//input");
    private final By passwordInput = By.xpath("(//input[@type='password'])[1]");
    private final By confirmPasswordInput = By.xpath("(//input[@type='password'])[2]");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By searchUsernameInput = By.xpath("(//label[normalize-space()='Username']/../following-sibling::div//input)[1]");
    private final By searchButton = By.xpath("//button[normalize-space()='Search']");
    private final By tableRows = By.cssSelector(".oxd-table-card");
    private final By deleteButton = By.cssSelector(".oxd-icon.bi-trash");
    private final By confirmDeleteButton = By.xpath("//button[normalize-space()='Yes, Delete']");
    private final By noRecordsFound = By.xpath("//span[normalize-space()='No Records Found']");
    private final By systemUsersHeader = By.xpath("//h5[normalize-space()='System Users'] | //h6[normalize-space()='System Users']");

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void openAdminModule() {
        click(adminMenu);
        waitForUrlContains("/admin");
    }

    public void createSystemUser(String employeeName, String username, String password) {
        openAdminModule();
        click(addButton);
        selectDropdownWithKeyboard(userRoleDropdown);
        type(employeeNameInput, employeeName);
        waitForElement(employeeNameInput).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        selectDropdownWithKeyboard(statusDropdown);
        type(usernameInput, username);
        type(passwordInput, password);
        type(confirmPasswordInput, password);
        click(saveButton);
    }
    public void navigateToAdmin() {
        click(adminMenu);
        waitForUrlContains("/admin");
    }

    public boolean searchUser(String username) {
        openAdminModule();
        type(searchUsernameInput, username);
        click(searchButton);
        return !driver.findElements(tableRows).isEmpty();
    }

    public void deleteUser(String username) {
        if (searchUser(username)) {
            click(deleteButton);
            click(confirmDeleteButton);
        }
    }

    public boolean isUserAbsent(String username) {
        openAdminModule();
        type(searchUsernameInput, username);
        click(searchButton);
        return isDisplayed(noRecordsFound);
    }
}
