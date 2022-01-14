package com.ea.framework.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ea.framework.driver.DriverManager;
import com.ea.framework.logger.LoggerManager;
import com.ea.framework.waits.ExplicitWaitFactory;
import com.ea.framework.waits.WaitType;

public class BasePage {

	
	
	protected void click(By by, WaitType wait, String elementname) 
	{
		WebElement element = ExplicitWaitFactory.performExplicitWait(wait, by);
		element.click();
		LoggerManager.info(elementname + " is clicked");
	}
	
	protected void sendKeys(By by, String value, WaitType wait, String elementname) 
	{
		WebElement element= ExplicitWaitFactory.performExplicitWait(wait, by);
	    element.sendKeys(value);
	    LoggerManager.info(value +" is entered successfully in "+ elementname);
	}
	
	protected String getPageTitle()
	{
		return DriverManager.getDriver().getTitle();
	}
	
	
	
}
