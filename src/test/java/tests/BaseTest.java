package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.Page;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public abstract class BaseTest {

    protected Config config = new Config();

    protected WebDriverWait wait;

    protected WebDriver driver;

    @BeforeMethod
    public void setUp(Method context) {
        driver = DriverFactory.get(new Config());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(Integer.parseInt(config.get("browser.width")), Integer.parseInt(config.get("browser.height"))));

        this.wait = new WebDriverWait(driver, Page.WAIT_TIMEOUT);

        // Go to the test URL
        driver.get(config.get("url") + getPath());

        // Close cookies
        this.closeCookies();
    }

    private void closeCookies() {
        WebElement cookies = driver.findElement(By.className("css-viglil"));
        wait.until(ExpectedConditions.visibilityOf(cookies));
        cookies.click();
    }

    private void saveEnvironment(Method context) {
        try {
            String description = context.getDeclaredAnnotation(org.testng.annotations.Test.class).description();

            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            String browserName = caps.getBrowserName();
            String browserVersion = caps.getVersion();

            log.info("[TEST] Browser: " + browserName + " " + browserVersion);
            log.info("[TEST] Test started: " + context.getName());
            log.info("[CASE] " + description);
            log.info("[ACTION] Loading URL: " + config.get("url") + getPath());

            FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/allure-results/environment.properties");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Browser=" + browserName);
            printWriter.println("Browser.Version=" + browserVersion);
            printWriter.println("Stand=" + config.get("environment"));
            printWriter.close();
        } catch (IOException e) {
            log.error("Cannot write to environment file");
        }

    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterMethod
    public void tearDown(Method context, ITestResult res) throws IOException {
        if (ITestResult.FAILURE == res.getStatus()) {
            byte[] src = saveScreenshot();

            LogEntries logs = driver.manage().logs().get(LogType.BROWSER);

            for (LogEntry logEntry : logs) {
                Allure.addAttachment("Browser console", "text/plain", logEntry.getMessage());
            }

            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
            String time = dateFormat.format(now);

            FileUtils.writeByteArrayToFile(new File("./target/screenshot/" + time + res.getName() + ".png"), src);

            log.info("[SCREENSHOT] Successfully captured a screenshot " + time + res.getName() + ".png");
        }

        // Save environment file
        this.saveEnvironment(context);

        if (Boolean.parseBoolean(config.get("browser.close"))) {
            this.driver.close();
        }

    }

    protected abstract String getPath();
}
