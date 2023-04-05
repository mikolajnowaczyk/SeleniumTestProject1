package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.BrowserTabsPageObject;
import pageObjects.homepage.Homepage;

public class BrowserTabs extends Hooks {
	public BrowserTabs() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void browserTabsTest(Method method) throws IOException, InterruptedException {
		startTest("Open another tab from button", method.getName());
		ExtentTestManager.log("Starting another tab test...");
		Homepage home = new Homepage();
		BrowserTabsPageObject browserTabsPO = new BrowserTabsPageObject();

		home.getBrowserTabLink().click();
		browserTabsPO.getOpenTabButton().click();
		ArrayList<String> tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.google.com/?");
		ExtentTestManager.pass("Test passed!");
	}

	@Test(dependsOnMethods = "browserTabsTest", groups = { "Regression" })
	public void browserTabsJuggling(Method method) throws IOException, InterruptedException {
		ArrayList<String> tabs;
		startTest("Open another tab from button", method.getName());
		ExtentTestManager.log("Starting juggling tabs test...");
		Homepage home = new Homepage();
		BrowserTabsPageObject browserTabsPO = new BrowserTabsPageObject();
		// by click on page
		home.getBrowserTabLink().click();
		browserTabsPO.getOpenTabButton().click();
		tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(1));
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.google.com/?");
		// new page
		getDriver().switchTo().newWindow(WindowType.TAB);
		Thread.sleep(1000);
		tabs = new ArrayList<String>(getDriver().getWindowHandles());
		getDriver().switchTo().window(tabs.get(2));
		getDriver().navigate().to("https://facebook.com");
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.facebook.com/");
		getDriver().close();
		getDriver().switchTo().window(tabs.get(1));
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.google.com/?");
		getDriver().close();
		getDriver().switchTo().window(tabs.get(0));
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://automationtesting.co.uk/browserTabs.html");
		ExtentTestManager.pass("Test passed!");
	}
}
