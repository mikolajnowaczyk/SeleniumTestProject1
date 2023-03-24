package uk.co.automationtesting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.ShopHomepage;
import pageObjects.ShopLoginPage;
import pageObjects.ShopYourAccount;

@Listeners(resources.Listeners.class)
public class ShopLoginTest extends Hooks {
	public ShopLoginTest() throws IOException {
		super();
	}

	@Test
	public void shopLoginTest() throws InterruptedException, IOException {
		ExtentManager.log("Starting ShopLoginTest...");
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getLoginButton().click();
		ExtentManager.pass("Reached the login page");

		FileInputStream workbookLocation = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\credentials.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(workbookLocation);
		XSSFSheet sheet = workbook.getSheetAt(0);
		for (int i = 0; i < 3; i++) {
			Row row = sheet.getRow(i + 1);
			Cell cellC0 = row.getCell(0);
			Cell cellC1 = row.getCell(1);

			String emailRow = cellC0.toString();
			String passRow = cellC1.toString();

			ShopLoginPage loginPage = new ShopLoginPage();
			loginPage.getEmail().sendKeys(emailRow);
			loginPage.getPassword().sendKeys(passRow);
			loginPage.getSubmitButton().click();

			ShopYourAccount yourAcc = new ShopYourAccount();
			try {
				yourAcc.getSignOut().click();
				ExtentManager.pass("user " + emailRow + " HAS signed in");
			} catch (Exception e) {
				ExtentManager.fail("user could NOT sign in");
				Assert.fail();
			}
		}

	}
}
