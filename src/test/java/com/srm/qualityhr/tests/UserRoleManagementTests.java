package com.srm.qualityhr.tests;

import com.srm.qualityhr.base.BaseTest;
import com.srm.qualityhr.pages.AdminPage;
import com.srm.qualityhr.pages.LoginPage;
import com.srm.qualityhr.pages.PIMPage;
import com.srm.qualityhr.utils.TestDataFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserRoleManagementTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() {
        new LoginPage(getDriver()).open().loginWithDefaultAdmin();
    }

    @Test
    public void createVerifyAndDeleteSystemUser() {
        String suffix = TestDataFactory.uniqueSuffix();
        String firstName = "Ess" + suffix;
        String lastName = "User";
        String username = "ess" + suffix.substring(Math.max(0, suffix.length() - 8));
        String password = "OrangeHRM@123";

        PIMPage pimPage = new PIMPage(getDriver());
        pimPage.navigateToPIM();
        String employeeName = pimPage.addEmployee(firstName, lastName, suffix.substring(Math.max(0, suffix.length() - 6)));

        AdminPage adminPage = new AdminPage(getDriver());
        adminPage.navigateToAdmin();
        adminPage.createSystemUser(employeeName, username, password);
        Assert.assertTrue(adminPage.searchUser(username), "Created system user was not found in admin list");

        adminPage.deleteUser(username);
        Assert.assertTrue(adminPage.isUserAbsent(username), "Deleted system user still appears in admin list");
    }
}
