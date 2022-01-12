package com.ea.framework.driver;

import java.util.Objects;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.ea.framework.utils.PropertyUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {


	private Driver() {}

	public static void initializeBrowser() 
	{
		String browser = PropertyUtils.get("browser");
		if(Objects.isNull(DriverManager.getDriver()))
		{
			if(browser.equalsIgnoreCase("Chrome"))
			{
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				WebDriverManager.chromedriver().setup();
				DriverManager.setDriver(new ChromeDriver());
			}
			else if(browser.equalsIgnoreCase("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				DriverManager.setDriver(new FirefoxDriver());
			}
			else if(browser.equalsIgnoreCase("IE"))
			{
				WebDriverManager.iedriver().setup();
				DriverManager.setDriver(new InternetExplorerDriver());
			}
			else if(browser.equalsIgnoreCase("Edge"))
			{
				System.setProperty(EdgeDriverService.EDGE_DRIVER_LOG_PROPERTY, "false");
				WebDriverManager.edgedriver().setup();
				DriverManager.setDriver(new EdgeDriver());
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
