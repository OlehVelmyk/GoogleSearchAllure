package com.google.tests;


import com.google.utils.DataConverter;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.google.actionHelpers.TakeScreenshot.takeScreenshot;
import static com.google.utils.DriverFactory.initDriver;


//@Listeners({TestStatuses.class})
public abstract class BaseTest {
    protected WebDriver driver;
    private String baseUrl = "https://www.google.com/";
    private String browserType;

    public WebDriver getActiveDriver() {
        return this.driver;
    }

    public String getBrowserType() {
        return browserType;
    }

    public void setBrowserType(String browserType) {
        this.browserType = browserType;
    }


    @BeforeClass
    @Parameters("browser")
    public void setUpDriver(@Optional("chrome") String browser) {
        driver = initDriver(browser);
        setBrowserType(browser);

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void goToPage() {
        Allure.step("GO TO " + baseUrl);
        driver.get(baseUrl);
        if (!getBrowserType().equalsIgnoreCase("ie")) {
            Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
        } else {
            Assert.assertEquals(DataConverter.parseTextValue(driver.getCurrentUrl(),
                    "^\\w{5,}\\W{3,}\\w{3,}\\.\\w{6,}\\.\\w{3,}\\/"), baseUrl);
        }
    }

    protected  void updateTestCaseName(String testName){
        Allure.getLifecycle().updateTestCase(result -> {
            result.setName(testName);
        });
    }

//    @AfterMethod
    public void onTestFailure(ITestResult iTestResult) throws IOException {
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            File screenshot = takeScreenshot(iTestResult.getInstanceName(), driver, browserType);
            Allure.addAttachment("Page Screenshot", FileUtils.openInputStream(screenshot));
        }
    }
}
