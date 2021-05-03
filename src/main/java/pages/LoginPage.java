package pages;

import data.builder.login.Login;
import io.qameta.allure.Step;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

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

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	public void doLogin(Login useCase ) {
		this.redirect(this.getUrl());
		this.checkUrl();
		this.typeUsername(useCase);
		this.typePassword(useCase);
		this.submit();
	}

	@Step("Fill in username field with {email}")
	public void typeUsername(Login useCase) {
		usernameField.sendKeys(useCase.getCredentials().getEmail());
	}

	@Step("Fill in password field with {password}")
	public void typePassword(Login useCase) {
		passwordField.sendKeys(useCase.getCredentials().getPassword());
	}

	@Step("Click on a submit buttons")
	public void submit() {
		this.click(submitButton);
	}

	public String getUrl() {
		return "/login";
	}
}