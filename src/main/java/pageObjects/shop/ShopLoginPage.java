package pageObjects.shop;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ShopLoginPage extends BasePage {
	public WebDriver driver;

	By email = By.cssSelector("section input[name='email']");
	By password = By.cssSelector("input[name='password']");
	By submitButton = By.cssSelector("button#submit-login");
	By errorMessage = By.cssSelector(".alert.alert-danger");

	public ShopLoginPage() throws IOException {
		super();
	}

	public WebElement getEmail() throws IOException {
		this.driver = getDriver();
		return driver.findElement(email);
	}

	public WebElement getPassword() throws IOException {
		this.driver = getDriver();
		return driver.findElement(password);
	}

	public WebElement getSubmitButton() throws IOException {
		this.driver = getDriver();
		return driver.findElement(submitButton);
	}

	public WebElement getErrorMessage() throws IOException {
		this.driver = getDriver();
		return driver.findElement(errorMessage);
	}
}
