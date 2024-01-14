package com.google.pages;

import com.google.logging.CustomReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected final Duration timeoutCommon = Duration.ofSeconds(10);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForClickable(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForLocated(By locator, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void actionClickElement(String logAction, By locator, Duration timeout) {
        CustomReporter.logAction(logAction);
        waitForClickable(locator, timeout);
        driver.findElement(locator).click();
    }

    public void actionFillField(String logAction, By locator, String inputValue, Duration timeout) {
        CustomReporter.logAction(logAction);
        waitForClickable(locator, timeout);
        driver.findElement(locator).sendKeys(inputValue);
    }

    public void actionFillField(String logAction, By locator, Keys buttonName, Duration timeout) {
        CustomReporter.logAction(logAction);
        waitForClickable(locator, timeout);
        driver.findElement(locator).sendKeys(buttonName);
    }

    public boolean elementIsPresent(String logAction, By locator) {
        CustomReporter.log(logAction);
        return !driver.findElements(locator).isEmpty();
    }

    public String actionGetText(By locator, Duration timeout) {
        waitForLocated(locator, timeout);
        return driver.findElement(locator).getText();
    }

    public List<WebElement> actionGetList(By locator, Duration timeout) {
        waitForClickable(locator, timeout);
        return driver.findElements(locator);
    }
}
