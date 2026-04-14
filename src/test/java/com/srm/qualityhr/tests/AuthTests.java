package com.srm.qualityhr.tests;

import com.srm.qualityhr.base.BaseTest;
import com.srm.qualityhr.dataproviders.TestDataProvider;
import com.srm.qualityhr.pages.DashboardPage;
import com.srm.qualityhr.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTests extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void verifyLoginScenarios(String scenario, String username, String password,
                                     boolean isValid, String expectedMessage) {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.login(username, password);

        if (isValid) {
            Assert.assertFalse(getDriver().getCurrentUrl().contains("/auth/login"),
                    "Expected successful login for scenario: " + scenario);
        } else {
            Assert.assertEquals(loginPage.getInvalidCredentialMessage(), expectedMessage,
                    "Unexpected validation message for scenario: " + scenario);
        }
    }

    @Test
    public void verifyLogoutRedirectsToLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.loginWithDefaultAdmin();
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        Assert.assertFalse(getDriver().getCurrentUrl().contains("/auth/login"), "Dashboard did not load after login");
        dashboardPage.logout();
        LoginPage loginPageAfterLogout = new LoginPage(getDriver());
        Assert.assertTrue(loginPageAfterLogout.isLoginPageDisplayed(),
                "Login page was not displayed after logout");
    }

    @Test
    public void verifyLoginWithEmptyCredentialsShowsValidation() {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.submitWithoutCredentials();
        Assert.assertEquals(loginPage.getValidationMessageCount(), 2,
                "Expected mandatory field validations for username and password");
    }
}
