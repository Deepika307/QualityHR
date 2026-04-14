package com.srm.qualityhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyInfoPage extends BasePage {

    private final By myInfoMenu = By.xpath("//span[normalize-space()='My Info']");
    private final By personalDetailsHeader = By.xpath("//h6[normalize-space()='Personal Details']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");

    public MyInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean openMyInfo() {
        click(myInfoMenu);
        return isDisplayed(personalDetailsHeader)
                || driver.getCurrentUrl().contains("viewPersonalDetails")
                || isDisplayed(userDropdown);
    }
}
