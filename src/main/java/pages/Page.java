package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.PropertyUtils;
import utils.WaitUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * Year: 2018-19
 *
 */
public class Page {
	public final static int IMPLICIT_WAIT = PropertyUtils.getIntegerProperty("implicitWait", 30);
	WaitUtils waitUtils;
	protected final AppiumDriver driver;

	/**
	 * A base constructor that sets the page's androidDriver
	 *
	 * The page structure is being used within this test in order to separate the
	 * page actions from the tests.
	 *
	 * Please use the AppiumFieldDecorator class within the page factory. This way annotations
	 * like @AndroidFindBy within the page objects.
	 *
	 * @param driver the appium androidDriver created in the beforesuite method.
	 */
	protected Page (AppiumDriver driver){
		this.driver = driver;
		initElements();
		loadProperties();
		waitUtils = new WaitUtils();
	}

	protected WebDriverWait wait;

	private void initElements() {
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(IMPLICIT_WAIT)), this);
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

	private void loadProperties() {
		//TODO: Add the properties.
	}

}
