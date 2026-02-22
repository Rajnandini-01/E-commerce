package tests;

import pages.LoginPage;
import org.testng.annotations.Test;

import base.BaseTest;

public class LoginTest extends BaseTest {

    @Test
    public void TC_001_validLogin() throws InterruptedException {
        LoginPage login = new LoginPage(getDriver());
        login.login("demouser", "testingisfun99");
    }

    @Test
    public void TC_002_invalidLogin() throws InterruptedException {
        LoginPage login = new LoginPage(getDriver());
        login.login("locked_user", "testingisfun99");
    }

    @Test
    public void TC_003_emptyLogin() {
        LoginPage login = new LoginPage(getDriver());
        login.clickLoginWithoutCredentials();
    }
}