package tests;

import cases.LoginTestCases;
import data.builder.login.Login;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.lang.reflect.Method;

@Slf4j
public class LoginTest extends BaseTest {

    private LoginPage loginPage;


    @BeforeMethod
    public void setUp(Method context) {
        super.setUp(context);

        loginPage = PageFactory.initElements(driver, LoginPage.class);

    }

    protected String getPath() {
        return "/login/";
    }

    @Test(description = "Log in as a valid user", dataProvider = "validCases", dataProviderClass = LoginTestCases.class)
    public void loginValidTest(Login /* Data type .. Instance of Login from package data.builder.  */ testCase /* Custom variable name */ ) {
        loginPage.doLogin(testCase);
    }


    }
}