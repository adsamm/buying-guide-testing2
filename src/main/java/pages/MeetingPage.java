package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WaitUtils;


import java.util.Random;

public class MeetingPage extends Page {

	/*@AndroidFindBy(id = "Více")
	private MobileElement moreButton;

	@AndroidFindBy(id = "Odhlášení")
	private MobileElement logoutButton;

	@AndroidFindBy(id = "Ano")
	private MobileElement yesButton;

	public MeetingPage(AppiumDriver driver) {
		super(driver);
	}

	@Step("Click on button Více")
	public void moreButtton() {
		this.click(moreButton);
	}

	@Step("Logout by clicking on button Odhlášení")
	public void logoutbutton() {
		this.click(logoutButton);
	}

	@Step("Click on button Yes to confirm")
	public void yesButton() {
		this.click(yesButton);
	} */

	private static final int NUMBER_OF_CONTACTS = 7;

	@AndroidFindBy(id = "createMeetingButton")
	private WebElement createMeetingButton;

	@AndroidFindBy(xpath = "//*[text()='dan']")
	private MobileElement firstContact;

	@AndroidFindBy(xpath = "//*[text()='Přejít nahoru']")
	private MobileElement goBackArrow;

	@AndroidFindBy(id = "pick_contact")
	private MobileElement pickContact;

	@AndroidFindBy(id = "newCustomerButton")
	private MobileElement newCustomerButton;

	@AndroidFindBy(xpath = "//*[text()='Jméno']")
	private MobileElement customerFirstName;

	@AndroidFindBy(xpath = "//*[text()='Předčíslí']")
	private MobileElement phonePrefix;

	@AndroidFindBy(xpath = "//*[text()='Příjmení']")
	private MobileElement customerSurName;

	@AndroidFindBy(xpath = "//*[text()='+420']")
	private MobileElement choseCzechPrefix;

	@AndroidFindBy(xpath = "//*[text()='Telefon']")
	private MobileElement phone;

	@AndroidFindBy(id = "email")
	private MobileElement email;

	@AndroidFindBy(id = "vatPayerTitle")
	private MobileElement toggleVAT;

	@AndroidFindBy(id = "generalAgreementSwitch")
	private MobileElement generalAgreement;

	@AndroidFindBy(id = "marketingAgreementSwitch")
	private MobileElement marketingAgreement;

	@AndroidFindBy(id = "saveButton")
	private MobileElement continueSaveButton;

	@AndroidFindBy(xpath = "//*[text()='Datum']")
	private MobileElement date;

	@AndroidFindBy(xpath = "//*[text()='Čas']")
	private MobileElement time;

	@AndroidFindBy(xpath = "//*[text()='Adresa']")
	private MobileElement address;

	@AndroidFindBy(xpath = "//*[text()='PSČ']")
	private MobileElement postCode;

	@AndroidFindBy(xpath = "//*[text()='Město']")
	private MobileElement town;

	@AndroidFindBy(xpath = "//*[text()='Stát']")
	private MobileElement state;

	@AndroidFindBy(xpath = "//*[text()='Česko']")
	private MobileElement stateCzech;

	@AndroidFindBy(id = "saveButton")
	private MobileElement saveDate;

	@AndroidFindBy(id = "material_timepicker_ok_button")
	private MobileElement saveTime;

	/**
	 * A base constructor that sets the page's androidDriver
	 * <p>
	 * The page structure is being used within this test in order to separate the
	 * page actions from the tests.
	 * <p>
	 * Please use the AppiumFieldDecorator class within the page factory. This way annotations
	 * like @AndroidFindBy within the page objects.
	 *
	 * @param driver the appium androidDriver created in the beforesuite method.
	 */
	public MeetingPage(AppiumDriver driver) {
		super(driver);
	}

	public void createMeeting() {
		createMeetingButton.click();
	}

	public void pickContact() {
		pickContact.click();
	}
	public void choseFirstContact() {
		firstContact.click();
	}
	public void newCustomer() {
		newCustomerButton.click();
	}

	public void customerFirstName() {
		driver.findElement(By.partialLinkText("Jméno")).sendKeys("Peter");
	}

	public void customerSurName() {
		driver.findElement(By.partialLinkText("Příjmení")).sendKeys("Tester");
	}

	public void phonePrefix() {
		phonePrefix.click();
	}

	public void choseCzechPrefix() {
		choseCzechPrefix.click();
	}

	public void email() {
		driver.findElement(By.partialLinkText("E-mail")).sendKeys("peter.tester@gmail.com");
	}

	public void toggleVAT() {
		toggleVAT.click();
	}

	public void generalAgreement() {
		generalAgreement.click();
	}

	public void marketingAgreement() {
		marketingAgreement.click();
	}

	public void continueSave() {
		continueSaveButton.click();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fillDate() {

		date.click();
		WebElement dateField = driver.findElement(By.xpath("//*[@id='textinput_placeholder']"));

		try {
			Thread.sleep(5);
			dateField.sendKeys("22.08.97");
			dateField.sendKeys(Keys.RETURN);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void saveDate() {
		try {
			Thread.sleep(5);
			saveDate.click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fillTime() {
		driver.findElement(By.id("material_hour_tv")).sendKeys("18");
		driver.findElement(By.id("material_minute_tv")).sendKeys("25");
	}

	public void saveTime() {
		saveTime.click();
	}

	public void fillAdress() {
		driver.findElement(By.linkText("Adresa")).sendKeys("Opletalova 38");
	}

	public void fillPostCode() {
		driver.findElement(By.linkText("PSČ")).sendKeys("13000");
	}

	public void fillTown() {
		driver.findElement(By.linkText("Město")).sendKeys("Praha");
	}

	public void clickState() {
		state.click();
	}

	public void choseStateCzech() {
		stateCzech.click();
	}

/*	@Step("Generate css locator for documents types in range NUMBER_OF_CONTACTS")
	public String randomContact() {
		Random rand = new Random();
		int int_random = rand.nextInt(NUMBER_OF_CONTACTS) + 1;
		//log.info(String.valueOf(int_random));
		System.out.println("//*[@class='android.view.ViewGroup']" + "[" + int_random + "]");
		return "//*[@class='android.view.ViewGroup']" + "[" + int_random + "]";

		//*[@class='android.view.ViewGroup'][9]

	public void choseRandomContact() {
		WebElement randomContact = driver.findElement(By.id(randomContact()));
		this.click(randomContact);
	} */

}