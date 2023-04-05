package uk.co.automationtesting.template;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;

public class EmptyTest extends Hooks {
	public EmptyTest() throws IOException {
		super();
	}

	@Test(groups = { "Smoke" })
	public void emptyTestRun1(Method method) throws InterruptedException {
		startTest("Empty log test 1", method.getName());
		ExtentTestManager.log("Starting empty log run...");
		Thread.sleep(1000);
		Assert.assertEquals(true, true);
		ExtentTestManager.pass("Empty log test 1 finished!");
	}

	@Test(groups = { "Smoke" })
	public void emptyTestRun2(Method method) throws InterruptedException {
		startTest("Empty log test 2", method.getName());
		ExtentTestManager.log("Starting empty log run...");
		Thread.sleep(1250);
		Assert.assertEquals(true, true);
		ExtentTestManager.pass("Empty log test 2 finished!");
	}

	@Test(groups = { "Smoke" })
	public void emptyTestRun3(Method method) throws InterruptedException {
		startTest("Empty log test 3", method.getName());
		ExtentTestManager.log("Starting empty log run...");
		Thread.sleep(1500);
		Assert.assertEquals(true, true);
		ExtentTestManager.pass("Empty log test 3 finished!");
	}

	@Test(groups = { "Smoke", "ABCD" })
	public void emptyTestRun4(Method method) throws InterruptedException {
		startTest("Empty test 4", method.getName());
		ExtentTestManager.log("Starting empty log run...");
		Thread.sleep(1750);
		Assert.assertEquals(true, true);
		ExtentTestManager.pass("Empty log test 4 finished!");
	}
}
