package com.ea.tests.pages;

import org.openqa.selenium.By;

import com.ea.framework.base.BasePage;
import com.ea.framework.report.ExtentLogger;
import com.ea.framework.waits.WaitType;

public final class LoginPage extends BasePage{

	
	private final By textboxusername = By.id("UserName");
	private final By textboxpassword = By.id("Password");
	private final By buttonlogin = By.xpath("//input[@value='Log in']");
	
	public LoginPage enterUserName(String username) throws Exception
	{
		sendKeys(textboxusername, username, WaitType.PRESENCE,"textboxusername");
		ExtentLogger.pass("Entered Username");
		return this;
	}
	
	public LoginPage enterPassword(String password) throws Exception
	{
		sendKeys(textboxpassword,password, WaitType.PRESENCE,"textboxpassword");
		ExtentLogger.pass("Entered Password");
		return this;
	}
	
	public LoginPage clickLoginButton() throws Exception
	{
		
		click(buttonlogin, WaitType.CLICKABLE,"buttonlogin");
		ExtentLogger.pass("Clicked Login Button");
		return this;
	}
	
	public String getTitle()
	{
		return getPageTitle();
	}
	
	
	
}
