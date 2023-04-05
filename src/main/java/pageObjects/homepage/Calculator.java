package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class Calculator extends BasePage {
	public WebDriver driver;

	public Calculator() throws IOException {
		super();
	}

	By resultField = By.cssSelector("input#result");

	public WebElement getResultField() throws IOException {
		this.driver = getDriver();
		return driver.findElement(resultField);
	}

	public WebElement getButton(char sign) throws IOException {
		this.driver = getDriver();
		return driver.findElement(By.cssSelector("input[value='" + sign + "']"));
	}
}
