package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class Buttons extends BasePage {
	public WebDriver driver;

	public Buttons() throws IOException {
		super();
	}

	By buttonOne = By.xpath("//button[@id='btn_one']");
	By buttonTwo = By.cssSelector("button#btn_two");
	By buttonThree = By.xpath("//button[@id='btn_three']");
	By buttonFour = By.xpath("//button[@id='btn_four']");

	public WebElement getButtonOne() throws IOException {
		this.driver = getDriver();
		return driver.findElement(buttonOne);
	}

	public WebElement getButtonTwo() throws IOException {
		this.driver = getDriver();
		return driver.findElement(buttonTwo);
	}

	public WebElement getButtonThree() throws IOException {
		this.driver = getDriver();
		return driver.findElement(buttonThree);
	}

	public WebElement getButtonFour() throws IOException {
		this.driver = getDriver();
		return driver.findElement(buttonFour);
	}
}
