package com.ea.framework.datareader;

public class DataManager {

	private DataManager()
	{}
		
	
	    private static ThreadLocal<String> testcasename = new ThreadLocal<>();
		
		public static String gettestCaseName()
		{
			return testcasename.get();
		}
		
		public static void settestCaseName(String testMethodName)
		{
			testcasename.set(testMethodName);
		}
		
		public static void unload()
		{
			testcasename.remove();
		}
	}


