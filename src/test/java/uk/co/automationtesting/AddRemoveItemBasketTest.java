package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();

		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();

		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getContinueShopBtn().click();
		shopProd.getHomepageLink().click();
		shopHome.getProdTwo().click();
		shopProd.getAddToCartBtn().click();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		cart.getDeleteItemTwo().click();
		cart.getTotalAmount().getText();

		waitForElementInvisible(cart.getDeleteItemTwo(), 5);
		Assert.assertEquals(cart.getTotalAmount().getText(), "$45.24");
	}
}
