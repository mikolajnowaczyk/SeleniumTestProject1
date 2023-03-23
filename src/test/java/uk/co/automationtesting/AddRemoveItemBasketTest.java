package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)
public class AddRemoveItemBasketTest extends Hooks {
	public AddRemoveItemBasketTest() throws IOException {
		super();
	}

	@Test
	public void addRemoveItem() throws InterruptedException, IOException {
		ExtentManager.log("Starting AddRemoveItemBasketTest...");
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		ExtentManager.pass("Reached the shop homepage");
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		ExtentManager.pass("Reached the shop product page");
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully incresed quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added product to basket");

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();
		ExtentManager.pass("Reached the checkout");

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		cart.getTotalAmount().getText();
		ExtentManager.pass("Have successfully deleted item in the cart");

		waitForElementInvisible(cart.getDeleteItemTwo(), 5);
		try {
			Assert.assertEquals(cart.getTotalAmount().getText(), "$45.23");
			ExtentManager.pass("The total amount matches the expected amount.");
		} catch (AssertionError e) {
			Assert.fail("Cart amount did not match the expected amount, it found " + cart.getTotalAmount().getText()
					+ " expected $45.23");
			ExtentManager.fail("Cart amount did not match the expected amount.");
		}
	}
}
