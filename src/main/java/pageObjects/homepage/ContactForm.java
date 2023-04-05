package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ContactForm extends BasePage {
	public WebDriver driver;

	public ContactForm() throws IOException {
		super();
	}

	By firstNameInput = By.cssSelector("form#contact_form > input[name='first_name']");
	By lastNameInput = By.cssSelector("form#contact_form > input[name='last_name']");
	By emailInput = By.cssSelector("form#contact_form > input[name='email']");
	By messageInput = By.cssSelector("form#contact_form > textarea[name='message']");
	By submitButton = By.cssSelector("[type='submit']");
	By resetButton = By.cssSelector("[type='reset']");
	By body = By.cssSelector("body");

	public WebElement getFirstNameInput() throws IOException {
		this.driver = getDriver();
		return driver.findElement(firstNameInput);
	}

	public WebElement getLastNameInput() throws IOException {
		this.driver = getDriver();
		return driver.findElement(lastNameInput);
	}

	public WebElement getEmailInput() throws IOException {
		this.driver = getDriver();
		return driver.findElement(emailInput);
	}

	public WebElement getMessageInput() throws IOException {
		this.driver = getDriver();
		return driver.findElement(messageInput);
	}

	public WebElement getResetBuElement() throws IOException {
		this.driver = getDriver();
		return driver.findElement(resetButton);
	}

	public WebElement getSubmitButton() throws IOException {
		this.driver = getDriver();
		return driver.findElement(submitButton);
	}

	public WebElement getBody() throws IOException {
		this.driver = getDriver();
		return driver.findElement(body);
	}
}
