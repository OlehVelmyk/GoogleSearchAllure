package com.google.logging;

import com.google.tests.BaseTest;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static com.google.actionHelpers.TakeScreenshot.takeScreenshot;

public class TestStatuses implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
//        Allure.step("Test " + "'" + iTestResult.getName() + "'" + " is started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
//        Allure.step("Test " + "'" + iTestResult.getName() + "'" + " success");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) currentClass).getActiveDriver();
        String browserType = ((BaseTest) currentClass).getBrowserType();
        File screenshot = takeScreenshot(iTestResult.getInstanceName(), webDriver, browserType);
        try {
            Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(screenshot));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Allure.step("Test " + "'" + iTestResult.getName() + "'" + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Allure.step("On Start " + "'" + iTestContext.getName() + "'");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Allure.step("On Finish " + "'" + iTestContext.getName() + "'");
    }
}
