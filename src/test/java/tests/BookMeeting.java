package tests;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.MeetingPage;

import java.net.MalformedURLException;
import java.net.URL;
import utils.BaseTest;

public class BookMeeting extends BaseTest {

    @Test(description = "Create a new meeting and contact")
    public void bookAppointment() {
        MeetingPage meetingPage = new MeetingPage(androidDriver);

        meetingPage.createMeeting();
        meetingPage.pickContact();
        meetingPage.choseFirstContact();
        meetingPage.newCustomer();
        meetingPage.customerFirstName();
        meetingPage.customerSurName();  
        meetingPage.email();
        meetingPage.toggleVAT();
        meetingPage.generalAgreement();
        //meetingPage.marketingAgreement();
        meetingPage.continueSave();
        //meetingPage.fillDate();
        //meetingPage.saveDate();
        //meetingPage.fillTime();
        // meetingPage.saveTime();
        meetingPage.fillAdress();
        meetingPage.fillPostCode();
        meetingPage.fillTown();
        meetingPage.clickState();
        meetingPage.choseStateCzech();
    }

    @BeforeTest
    @Override
    public void setUpPage() throws MalformedURLException {
        androidDriver = new AndroidDriver(new URL(APPIUM_SERVER_URL), getDesiredCapabilitiesForAndroid());
    }
}

