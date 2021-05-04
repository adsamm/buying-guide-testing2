package pages;

import data.builder.login.Login;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Data
@NoArgsConstructor
public class LoginPage extends Page {

	@FindBy(xpath = "//input[@name=\"email\"]")
	private WebElement usernameField;

	@FindBy(xpath = "//input[@name=\"password\"]")
	private WebElement passwordField;

	@FindBy(css = "button[data-testid=login-submit")
	private WebElement submitButton;

	@FindBy(className = "Toaster__message")
	private WebElement errorBox;

	protected LoginPage(AppiumDriver driver) {
		super(driver);
	}
}