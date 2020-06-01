package com.google.actionHelpers;

import com.google.dataProvider.DateProvider;
import com.google.utils.ReportFolderPath;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class TakeAndAddScreenshotToReport {

    public static File takeScreenshot(String className, WebDriver driver, String browserType) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String filePath = ReportFolderPath.getPathToReportFolder();
            File newFile = new File(filePath + browserType + "_" + className + "-"
                    + DateProvider.currentDate() + "_" + DateProvider.currentTime() + ".png");
            FileUtils.copyFile(scrFile, newFile);
            return newFile;
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        return null;
    }

    public static void addScreenshotToReportFile(File newFile) {
        try {
            Reporter.log("<br />" + "<a href=\"" + newFile.toString() + "\"><span style=\"color:#001dff; font-weight: bolder\" " +
                    "align=\"left\">FAILED SCREENSHOT FROM " + DateProvider.currentDate() + " " + DateProvider.currentTime() + "</span>" + "<br />");
            Reporter.log("<br> <a href='" + newFile.toString() + "'> <img style=\"border-style:groove\" src='" + newFile.toString() +
                    "' height='600' width='1000'/> </a><br />");
            Reporter.setCurrentTestResult(null);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Failed to capture screenshot: " + ex.getMessage());
        }
    }
}
