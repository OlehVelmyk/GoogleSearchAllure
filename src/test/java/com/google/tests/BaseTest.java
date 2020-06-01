package com.google.tests;

import com.google.logging.CustomReporter;
import com.google.logging.EventHandler;
import com.google.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static com.google.utils.DriverFactory.initDriver;


//@Listeners({TestStatuses.class})
public abstract class BaseTest {
    protected EventFiringWebDriver driver;
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
        driver = new EventFiringWebDriver(initDriver(browser));
        driver.register(new EventHandler());
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
        CustomReporter.logAction("GO TO " + baseUrl);
        driver.get(baseUrl);
        if (!getBrowserType().equalsIgnoreCase("ie")) {
            Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
        } else {
            Assert.assertEquals(DataConverter.parseTextValue(driver.getCurrentUrl(),
                    "^\\w{5,}\\W{3,}\\w{3,}\\.\\w{6,}\\.\\w{3,}\\/"), baseUrl);
        }
    }

    /*@AfterMethod
    public void onTestFailure(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
        Reporter.setCurrentTestResult(iTestResult);
        File file = TakeAndAddScreenshotToReport.takeScreenshot(iTestResult.getInstanceName(), driver, browserType);
        TakeAndAddScreenshotToReport.addScreenshotToReportFile(file);
        }
    }*/
}
