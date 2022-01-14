package com.ea.framework.listeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ea.framework.datareader.DataManager;
import com.ea.framework.logger.LoggerManager;
import com.ea.framework.report.ExtentLogger;
import com.ea.framework.report.ExtentReport;
import com.ea.framework.utils.LogUtils;

public class ListenerClass implements ITestListener, ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		LogUtils.deleteOldLogs();
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReport();
		LoggerManager.flushLogger();
		LogUtils.generateLog();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
		DataManager.settestCaseName(result.getMethod().getMethodName());
		LoggerManager.startTestLog(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		DataManager.unload();
		LoggerManager.endTestLog(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getMethod().getMethodName()+ " is passed");
		LoggerManager.info(result.getMethod().getMethodName()+ " is passed");
		LoggerManager.endTestLog(result.getMethod().getMethodName());
		DataManager.unload();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getMethod().getMethodName()+ " is failed");
		ExtentLogger.info(result.getThrowable().toString());
		ExtentLogger.info(Arrays.toString(result.getThrowable().getStackTrace()));
		LoggerManager.info(result.getMethod().getMethodName()+ " is failed");
		LoggerManager.info(result.getThrowable().toString());
		LoggerManager.endTestLog(result.getMethod().getMethodName());
		DataManager.unload();	
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getMethodName()+ " is skipped");
		LoggerManager.info(result.getMethod().getMethodName()+ " is skipped");
		LoggerManager.endTestLog(result.getMethod().getMethodName());
		DataManager.unload();
	}

}
