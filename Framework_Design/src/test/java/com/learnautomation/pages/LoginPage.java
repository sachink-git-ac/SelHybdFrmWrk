package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
	WebDriver driver;
	public LoginPage(WebDriver ldriver)
	{
		this.driver=ldriver;
	}
	
	@FindBy(name="email") WebElement uname;
	@FindBy(name="password") WebElement pass;
	@FindBy(xpath="//div[@class='ui fluid large blue submit button']") WebElement loginbtn;

	public void loginToCRM(String username, String password)
	{
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{		
			e.printStackTrace();
		}
		
		uname.sendKeys(username);
		pass.sendKeys(password);
		loginbtn.click();
		
		try 
		{
			Thread.sleep(5000);
		} 
		catch (InterruptedException e) 
		{		
			e.printStackTrace();
		}
		
	}
}
