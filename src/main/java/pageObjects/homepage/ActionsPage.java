package pageObjects.homepage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class ActionsPage extends BasePage {
	public WebDriver driver;

	public ActionsPage() throws IOException {
		super();
	}

	By doubleClickButton = By.cssSelector("[ondblclick] #doubClickStartText");
	By clickHoldArea = By.cssSelector("div#click-box");
//	By draggableElement = By.xpath("//p[contains(@id,'dragtarget')]");
	By draggableElement = By.xpath("//*[contains(text(),'Drag me!')]");
	By dropTarget1 = By.xpath("//div[contains(@class, 'droptarget')][1]");
	By dropTarget2 = By.xpath("//div[contains(@class, 'droptarget')][2]");
	By draggingInfoText = By.cssSelector("p#demo");
	By shiftHoldButton = By.xpath("//*[contains(text(),'Hold Shift & Click Here')]");

	public WebElement getDoubleClickArea() throws IOException {
		this.driver = getDriver();
		return driver.findElement(doubleClickButton);
	}

	public WebElement getClickHoldArea() throws IOException {
		this.driver = getDriver();
		return driver.findElement(clickHoldArea);
	}

	public WebElement getDraggableElement() throws IOException {
		this.driver = getDriver();
		return driver.findElement(draggableElement);
	}

	public WebElement getDropTarget1() throws IOException {
		this.driver = getDriver();
		return driver.findElement(dropTarget1);
	}

	public WebElement getDropTarget2() throws IOException {
		this.driver = getDriver();
		return driver.findElement(dropTarget2);
	}

	public WebElement getDraggingInfoText() throws IOException {
		this.driver = getDriver();
		return driver.findElement(draggingInfoText);
	}

	public WebElement getShiftHoldButton() throws IOException {
		this.driver = getDriver();
		return driver.findElement(shiftHoldButton);
	}

}