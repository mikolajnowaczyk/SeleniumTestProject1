package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class Accordion extends BasePage {
	public WebDriver driver;

	public Accordion() throws IOException {
		super();
	}

	By firstHeader = By.xpath("//div[@class='accordion']/div[contains(@class, 'accordion-header')][1]");
	By firstContent = By.xpath("//div[@class='accordion']/div[@class='accordion-content'][1]");
	By secondHeader = By.xpath("//div[@class='accordion']/div[contains(@class, 'accordion-header')][2]");
	By secondContent = By.xpath("//div[@class='accordion']/div[@class='accordion-content'][2]");
	By thirdHeader = By.xpath("//div[@class='accordion']/div[contains(@class, 'accordion-header')][3]");
	By thirdContent = By.xpath("//div[@class='accordion']/div[@class='accordion-content'][3]");

	public WebElement getFirstHeader() throws IOException {
		this.driver = getDriver();
		return driver.findElement(firstHeader);
	}

	public WebElement getFirstContent() throws IOException {
		this.driver = getDriver();
		return driver.findElement(firstContent);
	}

	public WebElement getSecondHeader() throws IOException {
		this.driver = getDriver();
		return driver.findElement(secondHeader);
	}

	public WebElement getSecondContent() throws IOException {
		this.driver = getDriver();
		return driver.findElement(secondContent);
	}

	public WebElement getThirdHeader() throws IOException {
		this.driver = getDriver();
		return driver.findElement(thirdHeader);
	}

	public WebElement getThirdContent() throws IOException {
		this.driver = getDriver();
		return driver.findElement(thirdContent);
	}
}
