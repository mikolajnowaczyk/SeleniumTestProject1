package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.ContactForm;
import pageObjects.homepage.Homepage;

public class ContactFormTest extends Hooks {
	public ContactFormTest() throws IOException {
		super();
	}

	@Test(groups = { "Smoke", "Regression" })
	public void sendSimpleMessage(Method method) throws InterruptedException, IOException {
		startTest("Contact form - simple message", method.getName());
		ExtentTestManager.log("Starting contact form test...");
		Homepage home = new Homepage();
		ContactForm form = new ContactForm();
		home.getContactUsLink().click();
		form.getFirstNameInput().sendKeys("Mikolaj");
		form.getLastNameInput().sendKeys("Nowak");
		form.getEmailInput().sendKeys("mik.now@wp.pl");
		form.getMessageInput().sendKeys("Simple message test!");
		form.getSubmitButton().click();
		Assert.assertTrue(form.getBody().getText().contains("Thank you for your mail!"));
		Assert.assertTrue(form.getBody().getText().contains(
				"We will be in contact shortly with a response. We aim to reply within 48 hours but our responses can sometimes be delayed during times of peak demand."));
		ExtentTestManager.pass("Simple message test finished!");
	}

	@Test(groups = { "Regression" })
	public void resetForm(Method method) throws InterruptedException, IOException {
		startTest("Contact form - reset form", method.getName());
		ExtentTestManager.log("Starting contact form test...");
		Homepage home = new Homepage();
		ContactForm form = new ContactForm();
		home.getContactUsLink().click();
		form.getFirstNameInput().sendKeys("Mikolaj");
		form.getLastNameInput().sendKeys("Nowak");
		form.getEmailInput().sendKeys("mik.now@wp.pl");
		form.getMessageInput().sendKeys("Simple message test!");
		form.getResetBuElement().click();
		Assert.assertTrue(form.getFirstNameInput().getText().isEmpty());
		Assert.assertTrue(form.getLastNameInput().getText().isEmpty());
		Assert.assertTrue(form.getEmailInput().getText().isEmpty());
		Assert.assertTrue(form.getMessageInput().getText().isEmpty());
		ExtentTestManager.pass("Contact form - reset form test finished!");
	}

	@Test(groups = { "Regression" })
	public void invalidEmailContactForm(Method method) throws InterruptedException, IOException {
		startTest("Contact form - reset form", method.getName());
		ExtentTestManager.log("Starting contact form test...");
		Homepage home = new Homepage();
		ContactForm form = new ContactForm();
		home.getContactUsLink().click();
		form.getFirstNameInput().sendKeys("Mikolaj");
		form.getLastNameInput().sendKeys("Nowak");
		form.getEmailInput().sendKeys("mik.nowwp.pl");
		form.getMessageInput().sendKeys("Simple message test!");
		form.getSubmitButton().click();
		Assert.assertTrue(form.getBody().getText().contains("Error: Invalid email address"));

		ExtentTestManager.pass("Contact form - invalid email test finished!");
	}
}
