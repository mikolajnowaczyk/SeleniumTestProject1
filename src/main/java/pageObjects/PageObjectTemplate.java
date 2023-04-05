package pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class PageObjectTemplate extends BasePage {
	public WebDriver driver;

	public PageObjectTemplate() throws IOException {
		super();
	}

	By exampleItem = By.cssSelector(".your-css-selector");

	public WebElement getExampleItem() throws IOException {
		this.driver = getDriver();
		return driver.findElement(exampleItem);
	}
}
