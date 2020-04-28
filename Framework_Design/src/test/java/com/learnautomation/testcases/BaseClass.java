package com.learnautomation.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass 
{
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setUpSuit()
	{
		Reporter.log("Setting up Reports and Test Started", true);
		excel=new ExcelDataProvider();
		config=new ConfigDataProvider();
		ExtentHtmlReporter reporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/FreeCRM_"+Helper.getCurrentDateTime()+".html");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		Reporter.log("Setting Done - Test can be started", true);
	}
	
	@Parameters({"browser","urlToBeTested"})
	@BeforeClass
	public void setUp(String browser, String url)
	{
		Reporter.log("Initalizing Browser and launching application", true);
		
		//driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getStageURL());
		// Above code for passing values from config file 
		//below changes are done to pass browser and url parameter from maven build / pom.xml
		
		driver=BrowserFactory.startApplication(driver, browser, url);
		
		Reporter.log("Browser Started and Application Launched", true);
	}
	
	@AfterClass
	public void tearDown()
	{	
		Reporter.log("Closing Browser", true);
		BrowserFactory.quitBrowser(driver);
		Reporter.log("Browser Closed", true);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws Exception
	{
		Reporter.log("Test is about to End, Capturing Screenshot", true);
		if(result.getStatus()==ITestResult.FAILURE)
		{
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			logger.skip("Test Skipped", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		extent.flush();
		Reporter.log("Test Completed, Resport Generated and Screenshot Captuerd", true);
	}

}
