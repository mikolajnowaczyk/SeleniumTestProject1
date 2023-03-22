package uk.co.automationtesting;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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

		Thread.sleep(1500);

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		cart.getHavePromo().click();
		cart.getPromoAddBtn().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		Thread.sleep(1500);
		cart.getProceedCheckoutBtn().click();

		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		Thread.sleep(1500);
		pInfo.getContinueBtn().click();

		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Wadowice");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		Thread.sleep(1500);
		orderDelivery.getContinueBtn().click();

		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am no in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();

		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
	}
}
