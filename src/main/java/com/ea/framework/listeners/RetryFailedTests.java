package com.ea.framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.ea.framework.utils.PropertyUtils;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count=0;
	
	
	
	@Override
	public boolean retry(ITestResult result) 
	{
		boolean value = false;
	    int retries;
		if(PropertyUtils.get("retrylogic").equalsIgnoreCase("yes")) 
		{
			retries = Integer.parseInt(PropertyUtils.get("retrycount")); 
			value = count<retries;
			count++;
		}
		return value;
	}

}
