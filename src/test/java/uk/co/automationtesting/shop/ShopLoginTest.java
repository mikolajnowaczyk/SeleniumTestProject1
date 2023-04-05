package uk.co.automationtesting.shop;

import static base.ExtentTestManager.startTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Homepage;
import pageObjects.shop.ShopHomepage;
import pageObjects.shop.ShopLoginPage;
import pageObjects.shop.ShopYourAccount;

@Listeners(resources.Listeners.class)
public class ShopLoginTest extends Hooks {
	public ShopLoginTest() throws IOException {
		super();
	}

	// prepare data for tests from excel sheet
	@DataProvider()
	public String[][] credentials() throws IOException {
		FileInputStream workbookLocation = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows() - 2;
		String[][] out = new String[totalRows][3];
		for (int rowNr = 0; rowNr < totalRows; rowNr++) {
			for (int colNr = 0; colNr < 3; colNr++) {
				out[rowNr][colNr] = sheet.getRow(rowNr + 1).getCell(colNr).toString();
			}
		}
		return (out);
	}

	@Test(dataProvider = "credentials", groups = { "Regression" }, priority = 1)
	public void shopLoginTest(String email, String password, String validAccount, Method method) throws IOException {
		startTest("Loging as " + email, method.getName());
		ExtentTestManager.log("Starting ShopLoginTest...");
		Homepage home = new Homepage();
		ShopHomepage shopHome = new ShopHomepage();
		ShopLoginPage loginPage = new ShopLoginPage();
		ShopYourAccount yourAcc = new ShopYourAccount();

		home.getTestStoreLink().click();
		shopHome.getLoginButton().click();
		ExtentTestManager.pass("Reached the login page");
		loginPage.getEmail().sendKeys(email);

		loginPage.getPassword().sendKeys(password);
		loginPage.getSubmitButton().click();
		if (validAccount.toLowerCase().equals("yes")) {
			try {
				yourAcc.getSignOut().click();
				ExtentTestManager.pass("user " + email + " HAS signed in");
			} catch (Exception e) {
				ExtentTestManager.fail("user could NOT sign in");
				Assert.fail();
			}
		} else {
			try {
				Assert.assertEquals(loginPage.getErrorMessage().getText(), "Authentication failed.");
				ExtentTestManager.pass("user " + email + " has correctly NOT signed in");
			} catch (Exception e) {
				ExtentTestManager.fail("user could somehow sign in");
				Assert.fail();
			}
		}
	}
}
