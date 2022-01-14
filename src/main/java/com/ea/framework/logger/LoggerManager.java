package com.ea.framework.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public final class LoggerManager {

	private LoggerManager() {}
	
	private static Logger logger = LogManager.getLogger();
	
	public static synchronized void startTestLog(String testName)
	{
		String logFileName = testName;
		ThreadContext.put("logFileName", logFileName);
		info("===================================================================");
		info("Execution Started for : "+testName);
	}
	
	public static synchronized void endTestLog(String testName)
	{
		info("Execution Completed for : "+testName);
		info("===================================================================");
	}
	
	private static String getCallInfo() {

		String callInfo;
		String className = Thread.currentThread().getStackTrace()[3].getClassName();
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		callInfo = className + ":" + methodName + ":";
		return callInfo;

	}
	
	public static void flushLogger()
	{
		LogManager.shutdown();
	}

	
	public static Logger getCurrentLog() {
		return logger;
	}
	
	public static void info(Object message) {

		getCurrentLog().info(getCallInfo()+ message);
	}

	public static void info(Object message, Throwable t) {
		getCurrentLog().info(getCallInfo()+ message, t);
	}
	
}
