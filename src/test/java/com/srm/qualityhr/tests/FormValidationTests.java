package com.srm.qualityhr.tests;

import com.srm.qualityhr.base.BaseTest;
import com.srm.qualityhr.pages.LeavePage;
import com.srm.qualityhr.pages.LoginPage;
import com.srm.qualityhr.pages.MyInfoPage;
import com.srm.qualityhr.pages.PIMPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormValidationTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        new LoginPage(getDriver()).open().loginWithDefaultAdmin();
    }

    @Test
    public void addEmployeeFormShowsMandatoryFieldErrors() {
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.submitEmptyEmployeeFormAndGetValidationCount();
        Assert.assertTrue(pimPage.hasRequiredFieldValidation(),
                "Expected mandatory field validations on empty Add Employee form");
    }

    @Test
    public void invalidDateFormatShowsValidation() {
        LeavePage leavePage = new LeavePage(getDriver());
        leavePage.enterInvalidDateFormat("2026-99-99");
        Assert.assertTrue(leavePage.isInvalidDateValidationDisplayed(),
                "Invalid date validation message was not shown");
    }

    @Test
    public void dropdownFieldsShowOptionsAndMyInfoModuleLoads() {
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.openPimModule();
        Assert.assertTrue(pimPage.getDropdownOptionCount(pimPage.getIncludeDropdown()) > 0,
                "Expected Include dropdown options to be visible");

        Assert.assertTrue(new MyInfoPage(getDriver()).openMyInfo(), "My Info page did not load");
    }
}
