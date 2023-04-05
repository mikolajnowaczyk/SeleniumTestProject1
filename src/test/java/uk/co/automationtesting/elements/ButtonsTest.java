package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Buttons;
import pageObjects.homepage.Homepage;

@Listeners(resources.Listeners.class)
public class ButtonsTest extends Hooks {
	public ButtonsTest() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void buttonOne(Method method) throws IOException, InterruptedException {
		startTest("Button one click - webelement", method.getName());
		ExtentTestManager.log("Starting buttons...");
		Homepage home = new Homepage();
		Buttons buttons = new Buttons();
		home.getButtonLink().click();
		buttons.getButtonOne().click();
		Assert.assertEquals("You clicked the first button!", getDriver().switchTo().alert().getText());
		ExtentTestManager.pass("Button one click test passed successfully!");
	}

	@Test(groups = { "Regression", "Smoke" })
	public void buttonTwo(Method method) throws IOException, InterruptedException {
		startTest("Button two click - javascript exxecutor", method.getName());
		ExtentTestManager.log("Starting buttons...");
		Homepage home = new Homepage();
		Buttons buttons = new Buttons();
		home.getButtonLink().click();
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", buttons.getButtonTwo());
		Assert.assertEquals("You clicked the second button!", getDriver().switchTo().alert().getText());
		ExtentTestManager.pass("Button two click test passed successfully!");
	}

	@Test(groups = { "Regression", "Smoke" })
	public void buttonThree(Method method) throws IOException, InterruptedException {
		startTest("Button three click - actions", method.getName());
		ExtentTestManager.log("Starting buttons...");
		Homepage home = new Homepage();
		Buttons buttons = new Buttons();
		Actions act = new Actions(getDriver());
		home.getButtonLink().click();
		act.moveToElement(buttons.getButtonThree()).click().build().perform();
		Assert.assertEquals("You clicked the third button!", getDriver().switchTo().alert().getText());
		ExtentTestManager.pass("Button three click test passed successfully!");
	}

	@Test(groups = { "Regression", "Smoke" })
	public void buttonFour(Method method) throws IOException, InterruptedException {
		startTest("Button four click - disabled", method.getName());
		ExtentTestManager.log("Starting buttons...");
		Homepage home = new Homepage();
		Buttons buttons = new Buttons();
		home.getButtonLink().click();
		Assert.assertEquals(buttons.getButtonFour().isEnabled(), false);
		ExtentTestManager.pass("Button four disability test passed successfully!");
	}
}
