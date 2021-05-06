package pages;

import data.builder.login.Login;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

@Data
@NoArgsConstructor
public class LoginPage extends Page {

	@AndroidFindBy(id = "Více")
	private MobileElement moreButton;

	@AndroidFindBy(id = "Odhlášení")
	private MobileElement logoutButton;

	@AndroidFindBy(id = "Ano")
	private MobileElement yesButton;

	public LoginPage(AppiumDriver driver) {
		super(driver);
	}

	public String getUrl() {
		return null;
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
	}
}