package pages;

import com.github.javafaker.Faker;
import config.Config;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.time.Duration;
import org.openqa.selenium.support.PageFactory;
import utils.PropertyUtils;
import utils.WaitUtils;

import java.text.SimpleDateFormat;

@Slf4j
@NoArgsConstructor
public abstract class Page {

	public static int WAIT_TIMEOUT = 54;

	/**
	 * Application filesystem location
	 */
	protected final String basePath = System.getProperty("user.dir");

	/**
	 * Web driver
	 */
	protected AppiumDriver driver;

	/**
	 * Wait driver object
	 */
	protected WebDriverWait wait;

	/**
	 * Config
	 */
	protected Config config = new Config();

	/**
	 * Faker
	 */
	protected Faker faker = new Faker();

	/**
	 * Date formatter
	 */
	protected SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Constructor
	 *
	 * @param driver Web driver
	 */
	public Page(AppiumDriver driver) {
		this.driver = driver;
		this.waitUtils = new WaitUtils();
	}

	/**
	 * Perform a click on an element with included wait for the element to be clickable
	 *
	 * @param element Clickable element
	 */
	protected void click(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			Assert.fail("Could not find an element " + element);
		}
	}

	public static final int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
	WaitUtils waitUtils;

}
