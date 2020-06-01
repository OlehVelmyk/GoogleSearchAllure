package com.google.logging;

import com.google.tests.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.google.actionHelpers.TakeAndAddScreenshotToReport.addScreenshotToReportFile;
import static com.google.actionHelpers.TakeAndAddScreenshotToReport.takeScreenshot;

public class TestStatuses implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        CustomReporter.log("Test " + "'" + iTestResult.getName() + "'" + " is started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        CustomReporter.log("Test " + "'" + iTestResult.getName() + "'" + " success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        CustomReporter.log("Test " + "'" + iTestResult.getName() + "'" + " failed");
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getActiveDriver();
        String browserType = ((BaseTest) currentClass).getBrowserType();
        addScreenshotToReportFile(takeScreenshot(iTestResult.getInstanceName(), webDriver, browserType));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        CustomReporter.log("Test " + "'" + iTestResult.getName() + "'" + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        CustomReporter.log("On Start " + "'" + iTestContext.getName() + "'");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        CustomReporter.log("On Finish " + "'" + iTestContext.getName() + "'");
    }
}
