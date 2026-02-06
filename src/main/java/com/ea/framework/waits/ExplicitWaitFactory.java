package com.ea.framework.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ea.framework.constants.FrameworkConstants;
import com.ea.framework.driver.DriverManager;

import java.time.Duration;

public final class ExplicitWaitFactory {

	private ExplicitWaitFactory() {}
	
	public static WebElement performExplicitWait(WaitType wait, By by)
	{
		WebElement element=null;
		if(wait==WaitType.CLICKABLE)
		{
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getWaitTime()))
			.until(ExpectedConditions.elementToBeClickable(by));
		}
		else if(wait==WaitType.PRESENCE)
		{
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getWaitTime()))
			.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		else if(wait==WaitType.VISIBLE)
		{
			element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(FrameworkConstants.getWaitTime()))
			.until(ExpectedConditions.visibilityOfElementLocated(by));
		}
		else if(wait==WaitType.NONE)
		{
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}
	
}
