package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.Homepage;

public class Hooks extends BasePage {
	public Hooks() throws IOException {
		super();
	}

	@BeforeMethod
	public void setup() throws IOException, InterruptedException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		getDriver().get(getUrl());
		Homepage home = new Homepage();
		home.getCookie().click();
	}

	@AfterMethod
	public void tearDown() {
		WebDriverInstance.cleanupDriver();
	}
}
