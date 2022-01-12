package com.ea.framework.report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ea.framework.constants.FrameworkConstants;

public class ExtentReport {

	private static ExtentReports extent;

	private ExtentReport()
	{
	}

	public static void initReports() 
	{
		if(Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getReportPath());
			extent.attachReporter(spark);
			extent.setSystemInfo("OS : ", System.getProperty("os.name"));
			extent.setSystemInfo("User Name : ", System.getProperty("user.name"));
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("EA Automation");
			spark.config().setReportName("Automation Report");
			spark.config().setTimelineEnabled(false);
			spark.config().enableOfflineMode(true);
		}
	}
	
	
	public static void flushReport() 
	{
		if(Objects.nonNull(extent))
		{
			extent.flush();
		}
		ExtentManager.unload();
		try {
			Desktop.getDesktop().browse(new File (FrameworkConstants.getReportPath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createTest(String testcasename)
	{
		ExtentTest test=extent.createTest(testcasename);
		ExtentManager.setExtentTest(test);
	}
}

