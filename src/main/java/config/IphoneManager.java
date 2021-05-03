package config;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class IphoneManager extends DriverManager {

    @Override
    public void createDriver() throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appiumVersion", "1.17.1");
        cap.setCapability("platformName", "iOS");
        cap.setCapability("deviceName", "iPhone.*");
        cap.setCapability("platformVersion", "13");
        cap.setCapability("udid", "508707ed6eb2c5eefc214fe29ee922aa3ffb1950"); //VYPLNIT UDID SVEHO ZARIZENI
        cap.setCapability("bundleId", "cz.t-mobile.oneapp");
        cap.setCapability("automationName", "XCUITest");
        cap.setCapability("launchTimeout", "5000");
        cap.setCapability("realDevice", "true");

        URL url = null;
        try {
            url = new URL("http://127.0.0.1:4723/wd/hub");
        } catch(MalformedURLException e) {
            System.out.println("The url is not well formed");
        }

        IOSDriver driver = new IOSDriver(url, cap);

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
}