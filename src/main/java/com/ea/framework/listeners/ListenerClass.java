package com.ea.framework.listeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ea.framework.datareader.DataManager;
import com.ea.framework.report.ExtentLogger;
import com.ea.framework.report.ExtentReport;

public class ListenerClass implements ITestListener, ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
		DataManager.settestCaseName(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		DataManager.unload();
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getMethod().getMethodName()+ " is passed");
		DataManager.unload();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(result.getMethod().getMethodName()+ " is failed");
		ExtentLogger.info(result.getThrowable().toString());
		ExtentLogger.info(Arrays.toString(result.getThrowable().getStackTrace()));
		DataManager.unload();	
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip(result.getMethod().getMethodName()+ " is skipped");
		DataManager.unload();
	}

}
