package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter reporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext context)
	{
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timeStamp+".html";
		
		reporter = new ExtentSparkReporter(".\\reports\\"+repName);
		reporter.config().setDocumentTitle("RestAssuredAutomationProject");
		reporter.config().setReportName("Pet Store Users API");
		reporter.config().setTheme(Theme.DARK);
		
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Application", "Pet Store Users API");
		reports.setSystemInfo("Operation System", System.getProperty("os.name"));
		reports.setSystemInfo("User Name", System.getProperty("user.name"));
		reports.setSystemInfo("User", "Shridhar");
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.PASS, "Test Passed");	
	}
	public void onTestFailure(ITestResult result)
	{
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = reports.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext context)
	{
		reports.flush();
	}
	
}
