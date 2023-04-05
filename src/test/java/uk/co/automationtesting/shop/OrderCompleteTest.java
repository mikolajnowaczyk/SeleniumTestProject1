package uk.co.automationtesting.shop;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Homepage;
import pageObjects.shop.OrderFormDelivery;
import pageObjects.shop.OrderFormPayment;
import pageObjects.shop.OrderFormPersInfo;
import pageObjects.shop.OrderFormShippingMethod;
import pageObjects.shop.ShopContentPanel;
import pageObjects.shop.ShopHomepage;
import pageObjects.shop.ShopProductPage;
import pageObjects.shop.ShoppingCart;

@Listeners(resources.Listeners.class)
public class OrderCompleteTest extends Hooks {
	public OrderCompleteTest() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void endToEndTest(Method method) throws InterruptedException, IOException {
		startTest("Oreder complete Test", method.getName());
		ExtentTestManager.log("Starting OrderCompleteTest...");
		Homepage home = new Homepage();
		home.getTestStoreLink().click();
		ExtentTestManager.pass("Have successfully reached store homepage");

		ShopHomepage shopHome = new ShopHomepage();
		shopHome.getProdOne().click();
		ExtentTestManager.pass("Have successfully reached shop product page");

		ShopProductPage shopProd = new ShopProductPage();
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		ExtentTestManager.pass("Have successfully selected product size");
		shopProd.getQuantIncrease().click();
		ExtentTestManager.pass("Have successfully increased quantity");
		shopProd.getAddToCartBtn().click();
		ExtentTestManager.pass("Have successfully added item to cart");

		ShopContentPanel cPanel = new ShopContentPanel();
		cPanel.getCheckoutBtn().click();

		ShoppingCart cart = new ShoppingCart();
		ExtentTestManager.pass("Have successfully reached the shoppign cart page");
		cart.getHavePromo().click();
		ExtentTestManager.pass("Have successfully selected the promo button");
		cart.getPromoAddBtn().click();
		cart.getPromoTextbox().sendKeys("20OFF");
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();
		ExtentTestManager.pass("Have successfully selected the check out button");

		OrderFormPersInfo pInfo = new OrderFormPersInfo();
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys("John");
		pInfo.getLastnameField().sendKeys("Smith");
		pInfo.getEmailField().sendKeys("johnsmith@test.com");
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();
		ExtentTestManager.pass("Have successfully entered customer details");

		OrderFormDelivery orderDelivery = new OrderFormDelivery();
		orderDelivery.getAddressField().sendKeys("123 Main Street");
		orderDelivery.getCityField().sendKeys("Wadowice");
		Select state = new Select(orderDelivery.getStateDropdown());
		state.selectByVisibleText("Texas");
		orderDelivery.getPostcodeField().sendKeys("77021");
		orderDelivery.getContinueBtn().click();
		ExtentTestManager.pass("Have successfully entered delivery informations");

		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod();
		shipMethod.getDeliveryMsgTextbox().sendKeys("If I am no in, please leave my delivery on my porch.");
		shipMethod.getContinueBtn().click();
		ExtentTestManager.pass("Have successfully selected shipping method");

		OrderFormPayment orderPay = new OrderFormPayment();
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		ExtentTestManager.pass("Have successfully placed order");
	}
}
