package com.ea.framework.Assertions;

import org.openqa.selenium.By;

import com.ea.framework.driver.DriverManager;
import com.ea.framework.report.ExtentLogger;

public final class ValidateTest {

	private ValidateTest() {}
	
	public boolean assertValidation(String actual, String expected) throws Exception
	{
		boolean value = true;
		if(actual.equals(expected))
		{
			ExtentLogger.pass("Actual: "+actual+" Expected: "+expected);
		}
		else
		{
			ExtentLogger.fail("Actual: "+actual+" Expected: "+expected);
			value=false;
		}
		return value;
	}
	
	public boolean assertValidation(By by, String elementName) throws Exception
	{
		boolean value = true;
		if(DriverManager.getDriver().findElement(by).isDisplayed())
		{
			ExtentLogger.pass(elementName+": is visible");
		}
		else
		{
			ExtentLogger.fail(elementName+": is not visible");
			value=false;
		}
		return value;
	}
}
