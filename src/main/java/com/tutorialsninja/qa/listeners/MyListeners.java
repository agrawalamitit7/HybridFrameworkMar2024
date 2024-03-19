package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.utils.ExtentReporter;
import com.tutorialninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
     
		extentReport = ExtentReporter.generateExtentReport();
		//System.out.println("Execution of Project Tests started");
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName=result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
		//System.out.println(testName + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//String testName=result.getName();
		extentTest.log(Status.PASS, testName + " got successfully executed");
		
		//System.out.println(testName + " got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//String testName=result.getName();
		WebDriver driver = null;
		
		try {
			driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//above line of code gives the driver instance

		String destinationScreenshotPath=Utilities.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,testName + " got failed");
		
		//System.out.println(testName + " got failed");
		//System.out.println(result.getThrowable());
		//System.out.println("Screenshot taken");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		//String testName=result.getName();
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP,testName + " got skipped");
		
//		System.out.println(testName + " got skipped");
//		System.out.println(result.getThrowable());
	}


	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		String pathOfExtentReport=System.getProperty("user.dir") + "\\test-output\\ExtentReports\\extentReport.html";
		File extentReport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        //System.out.println("Finished executing Project Tests");
	}

}
