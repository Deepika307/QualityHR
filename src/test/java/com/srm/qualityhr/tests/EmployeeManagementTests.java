package com.srm.qualityhr.tests;

import com.srm.qualityhr.base.BaseTest;
import com.srm.qualityhr.pages.LoginPage;
import com.srm.qualityhr.pages.PIMPage;
import com.srm.qualityhr.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeManagementTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        new LoginPage(getDriver()).open().loginWithDefaultAdmin();
    }

    @Test
    public void addSearchAndEditEmployeeRecord() {
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.navigateToPIM();
        String suffix = TestDataFactory.uniqueSuffix();
        String firstName = "John" + suffix;
        String lastName = "QA";
        String employeeId = suffix.substring(Math.max(0, suffix.length() - 6));

        String actualName = pimPage.addEmployee(firstName, lastName, employeeId);
        Assert.assertEquals(actualName, firstName + " " + lastName, "Added employee details are incorrect");

        Assert.assertTrue(pimPage.searchEmployeeByName(actualName), "Employee search did not return results");

        pimPage.openFirstEmployeeRecord();
        Assert.assertEquals(pimPage.getDisplayedEmployeeName(), actualName,
                "Employee record opened with an unexpected name");

        pimPage.updateMiddleName("Automation");
        Assert.assertEquals(pimPage.getMiddleName(), "Automation", "Middle name update was not saved");
    }

    @Test
    public void searchWithInvalidEmployeeNameShowsNoResults() {
        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.navigateToPIM();
        Assert.assertTrue(pimPage.searchInvalidEmployee("Invalid Employee 98765"),
                "Invalid employee search should show no records");
    }
}
