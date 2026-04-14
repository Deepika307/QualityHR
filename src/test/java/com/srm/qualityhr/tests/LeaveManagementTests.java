package com.srm.qualityhr.tests;

import com.srm.qualityhr.base.BaseTest;
import com.srm.qualityhr.pages.LeavePage;
import com.srm.qualityhr.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class LeaveManagementTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        new LoginPage(getDriver()).open().loginWithDefaultAdmin();
    }

    @Test
    public void navigateApplyLeaveAndVerifyRecord() {
        LeavePage leavePage = new LeavePage(getDriver());
        leavePage.navigateToLeavePage(); 
        Assert.assertTrue(leavePage.isLeavePageLoaded(), "Leave page did not load");
        LocalDate fromDate = LocalDate.now().plusDays(2);
        LocalDate toDate = LocalDate.now().plusDays(3);
        leavePage.applyLeave(fromDate, toDate, "Hackathon leave request");
        Assert.assertTrue(leavePage.isSubmissionSuccessful(),
                "Leave application was not submitted successfully");
        Assert.assertTrue(leavePage.isLeaveRecordVisible(),
                "Applied leave was not visible in My Leave list");
    }

    @Test
    public void applyingLeaveWithPastDatesShowsError() {
        LeavePage leavePage = new LeavePage(getDriver());
        leavePage.navigateToLeavePage(); 
        leavePage.applyLeave(LocalDate.now().minusDays(3),
                             LocalDate.now().minusDays(2),
                             "Past leave validation");
        Assert.assertTrue(leavePage.isPastDateErrorDisplayed(),
                "Past date validation did not appear for leave application");
    }
}
