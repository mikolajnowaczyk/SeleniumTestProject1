package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Calculator;
import pageObjects.homepage.Homepage;

public class CalculatorTest extends Hooks {

	private Calculator calc = new Calculator();

	private void insertNumber(int inumber) throws IOException {

		String snumber = Integer.toString(inumber);
		for (int i = 0; i < snumber.length(); i++) {
			calc.getButton(snumber.charAt(i)).click();
		}
	}

	private void insertSign(char sign) throws IOException {
		calc.getButton(sign).click();
	}

	private void checkValue(int value) throws IOException {
		Assert.assertEquals(Integer.parseInt(calc.getResultField().getText()), value);
	}

	@DataProvider(name = "addition-provider")
	public Object[][] dpMethod() {
		return new Object[][] { { "First-Value" }, { "Second-Value" } };
	}

	public CalculatorTest() throws IOException {
		super();
	}

	@Test(groups = { "Smoke", "Regression" })
	public void simpleAdditionTest(Method method) throws InterruptedException, IOException {
		startTest("Simple addition test", method.getName());
		ExtentTestManager.log("Starting calculator test...");
		Homepage home = new Homepage();

		home.getCalcLink().click();
		insertNumber(123456);
		insertSign('+');
		insertNumber(654321);
		insertSign('=');
		checkValue(777777);
	}
}
