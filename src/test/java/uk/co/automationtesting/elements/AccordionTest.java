package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.Accordion;
import pageObjects.homepage.Homepage;

@Listeners(resources.Listeners.class)
public class AccordionTest extends Hooks {
	public AccordionTest() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void accordionUnfoldingTest(Method method) throws IOException, InterruptedException {
		startTest("Accordion - unfolding", method.getName());
		ExtentTestManager.log("Starting accordion ...");
		Homepage home = new Homepage();
		Accordion accordion = new Accordion();
		home.getAccordionLink().click();
		Assert.assertFalse(accordion.getFirstContent().isDisplayed());
		Assert.assertFalse(accordion.getSecondContent().isDisplayed());
		Assert.assertFalse(accordion.getThirdContent().isDisplayed());
		ExtentTestManager.pass("Not displaying on enter");
		accordion.getFirstHeader().click();
		Assert.assertTrue(accordion.getFirstContent().isDisplayed());
		Assert.assertFalse(accordion.getSecondContent().isDisplayed());
		Assert.assertFalse(accordion.getThirdContent().isDisplayed());
		accordion.getSecondHeader().click();
		Assert.assertTrue(accordion.getFirstContent().isDisplayed());
		Assert.assertTrue(accordion.getSecondContent().isDisplayed());
		Assert.assertFalse(accordion.getThirdContent().isDisplayed());
		accordion.getThirdHeader().click();
		Assert.assertTrue(accordion.getFirstContent().isDisplayed());
		Assert.assertTrue(accordion.getSecondContent().isDisplayed());
		Assert.assertTrue(accordion.getThirdContent().isDisplayed());
		ExtentTestManager.pass("Unfolding simple test passed successfully!");
	}

	@Test(groups = { "Regression" }, dependsOnMethods = { "accordionUnfoldingTest" })
	public void accordionUnfoldingFoldingTest(Method method) throws IOException, InterruptedException {
		startTest("Accordion - unfolding all and folding", method.getName());
		ExtentTestManager.log("Starting accordion ...");
		Homepage home = new Homepage();
		Accordion accordion = new Accordion();
		home.getAccordionLink().click();
		accordion.getFirstHeader().click();
		accordion.getSecondHeader().click();
		accordion.getThirdHeader().click();
		ExtentTestManager.pass("Successfully unfolded content");
		accordion.getFirstHeader().click();
		waitForElementInvisible(accordion.getFirstContent(), 4);
		accordion.getSecondHeader().click();
		waitForElementInvisible(accordion.getSecondContent(), 4);
		accordion.getThirdHeader().click();
		waitForElementInvisible(accordion.getThirdContent(), 4);
		ExtentTestManager.pass("Unfolding and folding all test passed successfully!");
	}

	@Test(groups = { "Regression" }, dependsOnMethods = { "accordionUnfoldingTest" })
	public void accordionUnfoldingFoldingOneByOneTest(Method method) throws IOException, InterruptedException {
		startTest("Accordion - unfolding and folding one by one", method.getName());
		ExtentTestManager.log("Starting accordion ...");
		Homepage home = new Homepage();
		Accordion accordion = new Accordion();
		home.getAccordionLink().click();
		accordion.getFirstHeader().click();
		waitForElementVisibility(accordion.getFirstContent(), 4);
		accordion.getFirstHeader().click();
		waitForElementInvisible(accordion.getFirstContent(), 4);
		accordion.getSecondHeader().click();
		waitForElementVisibility(accordion.getSecondContent(), 4);
		accordion.getSecondHeader().click();
		waitForElementInvisible(accordion.getSecondContent(), 4);
		accordion.getThirdHeader().click();
		waitForElementVisibility(accordion.getThirdContent(), 4);
		accordion.getThirdHeader().click();
		waitForElementInvisible(accordion.getThirdContent(), 4);
		ExtentTestManager.pass("Unfolding and folding one by one test passed successfully!");
	}
}
