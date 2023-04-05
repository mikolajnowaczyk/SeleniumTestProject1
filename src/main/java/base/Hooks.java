package base;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.homepage.Homepage;

public class Hooks extends BasePage {
	public Hooks() throws IOException {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setup() throws IOException, InterruptedException {
		getDriver().get(getUrl());
		Homepage home = new Homepage();
		home.getCookie().click();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		WebDriverInstance.cleanupDriver();
	}
}
