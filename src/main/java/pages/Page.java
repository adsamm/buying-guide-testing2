package pages;

import com.github.javafaker.Faker;
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
	protected WebDriver driver;

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
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
	}

	/**
	 * Get expected url regular expression for this page
	 *
	 * @return Regular expression
	 */
	public abstract String getUrl();

	/**
	 * Wait until current page url matches provided regular expression
	 */
	public void checkUrl() {
		try {
			log.info("[ACTION][CHECK-URL]: " + getUrl());
			wait.until(ExpectedConditions.urlMatches(getUrl()));
		} catch (Exception e) {
			Assert.fail("Current url " + driver.getCurrentUrl() + " does not match provided condition " + getUrl());
		}
	}

	/**
	 * Redirect to a URL
	 *
	 * @param url URL
	 */
	protected void redirect(String url) {
		log.info("[ACTION][REDIRECT]" + url);
		driver.get(config.get("url") + url);
	}

	/**
	 * Find an element with included wait for the element to be visible
	 *
	 * @param by Selector
	 */
	protected WebElement get(By by) {
		try {
			log.info("[ACTION][GET]" + by);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return driver.findElement(by);
		} catch (Exception e) {
			Assert.fail("Could not find an element " + by);
			return null;
		}
	}

	/**
	 * Perform scroll to an element
	 *
	 * @param element Clickable element
	 */
	protected void scroll(WebElement element) {
		try {
			log.info("[ACTION][SCROLL]" + element);
			Coordinates cor = ((Locatable) element).getCoordinates();
			cor.inViewPort();
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("Could not scroll to an element " + element);
		}
	}

	/**
	 * Type in the text to the element with included wait for the element to be clickable
	 *
	 * @param element Clickable element
	 * @param text    Text which should be typed in
	 */
	protected void typeIn(WebElement element, String text) {
		try {
			log.info("[ACTION][TYPE IN]" + element);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(text);
		} catch (Exception e) {
			Assert.fail("Could not find an element " + element);
		}
	}

	/**
	 * Perform a click on an element with included wait for the element to be clickable
	 *
	 * @param element Clickable element
	 */
	protected void click(WebElement element) {
		try {
			log.info("[ACTION][CLICK]" + element);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (Exception e) {
			Assert.fail("Could not find an element " + element);
		}
	}

	/**
	 * Retrieve value of a form element after the element is visible
	 *
	 * @param element Form element
	 */
	protected String value(WebElement element) {
		try {
			log.info("[ACTION][VALUE] " + element);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getAttribute("value");
		} catch (Exception e) {
			Assert.fail("Could not find an element " + element);
			return null;
		}
	}

	/**
	 * Retrieve text of an element after the element is visible
	 *
	 * @param element Form element
	 */
	protected String text(WebElement element) {
		try {
			log.info("[ACTION][TEXT]: " + element);
			wait.until(ExpectedConditions.visibilityOf(element));
			return element.getText();
		} catch (Exception e) {
			Assert.fail("Could not find an element " + element);
			return null;
		}
	}

	/**
	 * Perform a check on an element with included wait for the element to be visible
	 *
	 * @param element Visible element
	 */
	protected void visible(WebElement element) {
		try {
			log.info("[ACTION][VISIBLE]" + element);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			Assert.fail("Element is not visible " + element);
		}
	}

	/**
	 * Perform a check on an element with included wait for the element to be invisible
	 *
	 * @param element Invisible element
	 */
	protected void invisible(WebElement element) {
		try {
			log.info("[ACTION][INVISIBLE]" + element);
			wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
			Assert.fail("Element is not invisible" + element);
		}
	}

}
