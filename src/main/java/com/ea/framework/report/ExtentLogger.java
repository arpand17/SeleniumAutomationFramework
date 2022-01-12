package com.ea.framework.report;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.ea.framework.utils.PropertyUtils;
import com.ea.framework.utils.ScreenshotUtils;

public final class ExtentLogger {

	private ExtentLogger() {}

	public static void info(String message)
	{
		ExtentManager.getExtentTest().info(message);
	}

	public static void fail(String message) 
	{
		if((PropertyUtils.get("failscreenshot")).equalsIgnoreCase("yes"))
		{
			ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
		}
		else
		{
			ExtentManager.getExtentTest().fail(message);
		}
	}

	public static void skip(String message) 
	{
		if((PropertyUtils.get("skipscreenshot")).equalsIgnoreCase("yes"))
		{
			ExtentManager.getExtentTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
		}
		else
		{
			ExtentManager.getExtentTest().skip(message);
		}
	}

	public static void pass(String message) 
	{
		if((PropertyUtils.get("passscreenshot")).equalsIgnoreCase("yes"))
		{
			ExtentManager.getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenshot()).build());
		}
		else
		{
			ExtentManager.getExtentTest().pass(message);
		}

	}
}

