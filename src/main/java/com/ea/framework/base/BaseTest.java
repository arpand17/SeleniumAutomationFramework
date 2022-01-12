package com.ea.framework.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ea.framework.driver.Driver;

public class BaseTest {
		
	// So that no other class can create any object but class extending it should have access
	// to below methods
	protected BaseTest() {
		
	}
	
	
	
	@BeforeMethod
	protected void setup()
	{
		Driver.initializeBrowser();
	}
	
	@AfterMethod
	protected void tearDown()
	{
		Driver.quitBrowser();
	}
}
