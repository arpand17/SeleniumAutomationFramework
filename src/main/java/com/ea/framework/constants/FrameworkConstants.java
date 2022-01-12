package com.ea.framework.constants;

import com.ea.framework.utils.PropertyUtils;

public final class FrameworkConstants {

	private FrameworkConstants()
	{
		
	}
	
	private static final String USERDIR=System.getProperty("user.dir");
	private static final String RESOURCESPATH = USERDIR+"/src/test/resources";
	private static final String CONFIGFILEPATH = RESOURCESPATH +"/config/GlobalConfig.properties";
	private static final String REPORTPATH = System.getProperty("user.dir") +"/extent-test-output";
	private static String reportFilePath="";
	private static final int EXPLICITWAIT=10;
	private static final String TESTDATAPATH = RESOURCESPATH +"/testdata/testdata.xlsx";
	private static final String RUNMANAGERPATH = USERDIR +"/RunManager.xlsx";


	
	
	
	public static String getResourcesFilePath()
	{
		return RESOURCESPATH;
	}
	
	public static String getConfigFilePath()
	{
		return CONFIGFILEPATH;
	}
	
	public static String getReportPath() 
	{
		if(reportFilePath.isEmpty())
		{
			reportFilePath=createReportPath();
		}
		return reportFilePath;
	}
	
	private static String createReportPath() 
	{
		if(PropertyUtils.get("overridereports").equalsIgnoreCase("no"))
		{
			return REPORTPATH+"/"+System.currentTimeMillis()+"_"+"testreport.html";
		}
		else
		{
			return REPORTPATH+"/"+"testreport.html";
		}
		
	}
	
	public static int getWaitTime()
	{
		return EXPLICITWAIT;
	}
	
	public static String getTestData()
	{
		return TESTDATAPATH;
	}
	
	public static String getRunManager()
	{
		return RUNMANAGERPATH;
	}
	
}
