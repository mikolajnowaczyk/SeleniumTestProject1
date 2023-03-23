package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentManager;
import base.Hooks;
import pageObjects.HomePage;
import pageObjects.OrderFormDelivery;
import pageObjects.OrderFormPayment;
import pageObjects.OrderFormPersInfo;
import pageObjects.OrderFormShippingMethod;
import pageObjects.ShopContentPanel;
import pageObjects.ShopHomepage;
import pageObjects.ShopProductPage;
import pageObjects.ShoppingCart;

@Listeners(resources.Listeners.class)
public class OrderCompleteTest extends Hooks {
	public OrderCompleteTest() throws IOException {
		super();
	}

	@Test
	public void endToEndTest() throws InterruptedException, IOException {
		ExtentManager.log("Starting OrderCompleteTest...");
		HomePage home = new HomePage();
		home.getCookie().click();
		home.getTestStoreLink().click();
		ExtentManager.pass("Have successfully reached store homepage");

		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();
		ExtentManager.pass("Have successfully reached shop product page");

		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentManager.pass("Have successfully added item to cart");

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		ExtentManager.pass("Have successfully reached the shoppign cart page");
		cart.getHavePromo().click();
		ExtentManager.pass("Have successfully selected the promo button");
		cart.getPromoAddBtn().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentManager.pass("Have successfully selected the check out button");

		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered customer details");

		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Wadowice");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		ExtentManager.pass("Have successfully entered delivery informations");

		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am no in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();
		ExtentManager.pass("Have successfully selected shipping method");

		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentManager.pass("Have successfully placed order");

	}
}
