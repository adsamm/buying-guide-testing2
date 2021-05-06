package tests;

import cases.LoginTestCases;
import data.builder.login.Login;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.lang.reflect.Method;

@Slf4j
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test(description = "Log in as a valid user")
    public void loginValidTest() {
       loginPage.moreButtton();
       loginPage.logoutbutton();
       loginPage.yesButton();
    }

    @AfterMethod
    public void tearDownAppium() throws Exception {
        System.out.println("\nTearing Down Driver.");
        driver.quit();
    }

}