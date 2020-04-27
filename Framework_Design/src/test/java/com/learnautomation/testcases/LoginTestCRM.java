package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.learnautomation.pages.LoginPage;

public class LoginTestCRM extends BaseClass
{
	
	@Test
	public void loginApp()
	{
		logger=extent.createTest("Login To CRM");
		LoginPage loginPage= PageFactory.initElements(driver, LoginPage.class);
		logger.info("Application is started");
		loginPage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		logger.pass("Login Sucess");
	}

}
