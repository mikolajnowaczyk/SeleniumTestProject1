package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class BrowserTabsPageObject extends BasePage {
	public WebDriver driver;

	public BrowserTabsPageObject() throws IOException {
		super();
	}

	By openTabButton = By.cssSelector("form[target='_blank'] > input[value='Open Tab']");

	public WebElement getOpenTabButton() throws IOException {
		this.driver = getDriver();
		return driver.findElement(openTabButton);
	}
}
