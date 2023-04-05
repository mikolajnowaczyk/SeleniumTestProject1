package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage;
import base.ExtentManager;
import base.ExtentTestManager;

public class Listeners extends BasePage implements ITestListener {
	public Listeners() throws IOException {
		super();
	}

	public synchronized void onStart(ITestContext context) {
		System.out.println("onStart");
		ExtentManager.getReports();
//		ExtentManager.createTest(context.getName(), context.getName());
	}

	public synchronized void onTestFailure(ITestResult result) {
		ExtentTestManager.getTest().fail(result.getThrowable());
		try {
			System.out.println("Test failed: " + result.getMethod().getMethodName());
			takeSnapShot(result.getName());
			ExtentTestManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void onFinish(ITestContext context) {
		System.out.println("onFinish function");
		ExtentManager.flushReport();
	}
}
