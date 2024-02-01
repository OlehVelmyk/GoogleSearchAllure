package com.google.actionHelpers;

import com.google.dataProvider.DateProvider;
import com.google.utils.ReportFolderPath;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class TakeScreenshot {

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
}
