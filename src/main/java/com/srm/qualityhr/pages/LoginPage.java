package com.srm.qualityhr.pages;

import com.srm.qualityhr.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content-text");
    private final By validationMessages = By.cssSelector(".oxd-input-field-error-message");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(ConfigReader.get("base.url"));
        waitForElement(usernameInput);
        return this;
    }

    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }

    public void loginWithDefaultAdmin() {
        login(ConfigReader.get("admin.username"), ConfigReader.get("admin.password"));
    }

    public void submitWithoutCredentials() {
        click(loginButton);
    }

    public String getInvalidCredentialMessage() {
        return getText(errorMessage);
    }

    public int getValidationMessageCount() {
        return driver.findElements(validationMessages).size();
    }

    public boolean isLoginPageDisplayed() {
    	 waitForElement(loginButton);  
    	    return isDisplayed(loginButton);
    }
}
