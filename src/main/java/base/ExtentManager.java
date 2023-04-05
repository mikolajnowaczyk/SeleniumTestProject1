package base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends BasePage {

	public static ExtentReports extentReport = ExtentManager.setupExtentReports();
	public static String extentReportPrefix;
//	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

	public ExtentManager() throws IOException {
		super();
	}

	public static ExtentReports getReports() {
		if (extentReport == null) {
			setupExtentReports();
		}
		return extentReport;
	}

	public static ExtentReports setupExtentReports() {
		extentReport = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/report/"
				+ extentReportsPrefix_Name("Selenium Test Project") + ".html");
		extentReport.attachReporter(spark);
		extentReport.setSystemInfo("Tester", "Mikolaj");
		spark.config().setReportName("Regression Test");
		spark.config().setDocumentTitle("Test results");
		spark.config().setTheme(Theme.DARK);

		return extentReport;
	}

	public static String extentReportsPrefix_Name(String testName) {
		String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		extentReportPrefix = testName + "_" + date;
		return extentReportPrefix;
	}

	public static void flushReport() {
		extentReport.flush();
	}

//	public synchronized static ExtentTest getTest() {
//		return extentTestMap.get((int) Thread.currentThread().getId());
//	}
//
//	public synchronized static ExtentTest createTest(String name, String description) {
//		ExtentTest test = extentReport.createTest(name, description);
//		extentTestMap.put((int) Thread.currentThread().getId(), test);
//		return getTest();
//	}
}
