package com.ea.tests.pages;

import org.openqa.selenium.By;

import com.ea.framework.base.BasePage;
import com.ea.framework.waits.WaitType;

public final class HomePage extends BasePage {
	
	private final By linkLogin = By.id("loginLink");
	
	public LoginPage clickOnLoginLink() throws Exception
	{
		click(linkLogin,WaitType.CLICKABLE, "LoginLink");
		return new LoginPage();
	}
	
	public String getTitle()
	{
		return getPageTitle();
	}
	

}
