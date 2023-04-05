package uk.co.automationtesting.shop;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Homepage;
import pageObjects.shop.ShopContentPanel;
import pageObjects.shop.ShopHomepage;
import pageObjects.shop.ShopProductPage;
import pageObjects.shop.ShoppingCart;

@Listeners(resources.Listeners.class)
public class AddRemoveItemBasketTest extends Hooks {
	public AddRemoveItemBasketTest() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void addRemoveItem(Method method) throws InterruptedException, IOException {
		startTest("Add and remove item from basket - successful", method.getName());
		ExtentTestManager.log("Starting AddRemoveItemBasketTest...");
		Homepage home = new Homepage();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		ExtentTestManager.pass("Reached the shop homepage");
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentTestManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentTestManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentTestManager.pass("Have successfully incresed quantity");
		shopProd.getAddToCartBtn().click();
		ExtentTestManager.pass("Have successfully added product to basket");

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		ExtentTestManager.pass("Reached the checkout");

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		cart.getTotalAmount().getText();
		ExtentTestManager.pass("Have successfully deleted item in the cart");

		waitForElementInvisible(cart.getDeleteItemTwo(), 5);
		try {
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
			ExtentTestManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found " + cart.getTotalAmount().getText()
					+ " expected $45.24");
			ExtentTestManager.fail("Cart amount did not match the expected amount.");
		}
	}

	@Test(groups = { "Regression" })
	public void addRemoveItemFailExample(Method method) throws InterruptedException, IOException {
		startTest("Add and remove item from basket - failure", method.getName());
		ExtentTestManager.log("Starting AddRemoveItemBasketTest...");
		Homepage home = new Homepage();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		ExtentTestManager.pass("Reached the shop homepage");
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentTestManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentTestManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentTestManager.pass("Have successfully incresed quantity");
		shopProd.getAddToCartBtn().click();
		ExtentTestManager.pass("Have successfully added product to basket");

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		ExtentTestManager.pass("Reached the checkout");

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		cart.getTotalAmount().getText();
		ExtentTestManager.pass("Have successfully deleted item in the cart");

		waitForElementInvisible(cart.getDeleteItemTwo(), 5);
		try {
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
			ExtentTestManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found " + cart.getTotalAmount().getText()
					+ " expected $45.23");
			ExtentTestManager.fail("Cart amount did not match the expected amount.");
		}
	}
}
