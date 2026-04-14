package com.srm.qualityhr.base;

import com.srm.qualityhr.listeners.ExtentManager;
import com.srm.qualityhr.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentManager.unload();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
