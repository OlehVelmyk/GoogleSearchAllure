package com.google.utils;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;

import java.io.File;

public class DriverFactory {

    public static WebDriver initDriver(String browser) {
        String osName = System.getProperty("os.name");
        if (osName.contains("nix")) {
            switch (browser) {
                case "firefox":
                    System.setProperty(
                            "webdriver.gecko.driver",
                            new File(DriverFactory.class.getResource("/" + "geckodriver").getFile()).getPath());
                    return new FirefoxDriver();
                case "opera":
                    System.setProperty(
                            "webdriver.opera.driver",
                            new File(DriverFactory.class.getResource("/" + "operadriver").getFile()).getPath());
                    return new OperaDriver();
                case "chrome":
                    System.setProperty(
                            "webdriver.chrome.driver",
                            new File(DriverFactory.class.getResource("/" + "chromedriver").getFile()).getPath());
                    return new ChromeDriver();
            }
        }
        if (osName.contains("Windows")) {
            switch (browser) {
                case "firefox":
                    System.setProperty(
                            "webdriver.gecko.driver",
                            new File(DriverFactory.class.getResource("/geckodriver.exe").getFile()).getPath().replaceAll("\\\\", "/"));
                    return new FirefoxDriver();
                case "opera":
                    System.setProperty(
                            "webdriver.opera.driver",
                            new File(DriverFactory.class.getResource("/operadriver.exe").getFile()).getPath().replaceAll("\\\\", "/"));
                    return new OperaDriver();
                case "chrome":
                    System.setProperty(
                            "webdriver.chrome.driver",
                            new File(DriverFactory.class.getResource("/chromedriver.exe").getFile()).getPath().replaceAll("\\\\", "/"));
                    return new ChromeDriver();
                case "edge":
                    System.setProperty(
                            "webdriver.msedge.driver",
                            new File(DriverFactory.class.getResource("/msedgedriver.exe").getFile()).getPath().replaceAll("\\\\", "/"));
                    return new EdgeDriver();
                case "ie":
                case "internet explorer":
                    System.setProperty(
                            "webdriver.ie.driver",
                            new File(DriverFactory.class.getResource("/IEDriverServer.exe").getFile()).getPath().replaceAll("\\\\", "/"));
                    InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                            //.destructivelyEnsureCleanSession()
                            .setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT)
                            .enablePersistentHovering()
                            .requireWindowFocus();
                    ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                    return new InternetExplorerDriver(ieOptions);
            }
        }
        return new ChromeDriver();
    }
}