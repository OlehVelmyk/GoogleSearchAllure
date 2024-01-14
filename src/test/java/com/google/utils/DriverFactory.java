package com.google.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;


public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "opera":
//                WebDriverManager.operadriver().setup();
//                ChromeOptions operaOptions = new ChromeOptions()
//                        .addArguments("allow-elevated-browser")
//                        .setExperimentalOption("w3c", true);
//                return new OperaDriver(operaOptions);
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            case "ie":
            case "internet explorer":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                        //.destructivelyEnsureCleanSession()
                        .setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT)
                        .enablePersistentHovering()
                        .requireWindowFocus();
                ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                return new InternetExplorerDriver(ieOptions);
        }
        return new ChromeDriver();
    }
}