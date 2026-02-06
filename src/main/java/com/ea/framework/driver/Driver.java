package com.ea.framework.driver;

import com.ea.framework.utils.PropertyUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Objects;

public class Driver {


	private Driver() {}

	public static void initializeBrowser() 
	{
		String browser = PropertyUtils.get("browser");

		if(Objects.isNull(DriverManager.getDriver()))
		{
			if(browser.equalsIgnoreCase("Chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--start-maximized");
				DriverManager.setDriver(new ChromeDriver(options));
			}
			else if(browser.equalsIgnoreCase("Firefox"))
			{
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--start-maximized");
				DriverManager.setDriver(new FirefoxDriver(options));
			}
			else if(browser.equalsIgnoreCase("Edge"))
			{
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--start-maximized");
				DriverManager.setDriver(new EdgeDriver(options));
			}
			DriverManager.getDriver().manage().window().maximize();
			DriverManager.getDriver().get(PropertyUtils.get("url"));
		}
	}









	public static void quitBrowser()
	{
		if(Objects.nonNull(DriverManager.getDriver()))
		{
			DriverManager.getDriver().quit();
			DriverManager.unload();
		}
	}
}
