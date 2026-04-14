package com.srm.qualityhr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    private final By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By sideMenu = By.cssSelector(".oxd-sidepanel");
    private final By logoutLink = By.xpath("//a[normalize-space()='Logout']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return !driver.getCurrentUrl().contains("/auth/login");
    }

    public void logout() {
        click(userDropdown);
        click(logoutLink);
    }
}
