package resources;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BasePage;
import base.ExtentManager;

public class Listeners extends BasePage implements ITestListener {
	public Listeners() throws IOException {
		super();
	}

	public synchronized void onStart(ITestContext context) {
		ExtentManager.getReports();
		ExtentManager.createTest(context.getName(), context.getName());
	}

	public synchronized void onTestFailure(ITestResult result) {
		ExtentManager.getTest().fail(result.getThrowable());
		try {
			System.out.println("Test failed: " + result.getMethod().getMethodName());
			takeSnapShot(result.getName());
			ExtentManager.attachImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void onFinish(ITestContext context) {
		ExtentManager.flushReport();
	}
}
