package com.google.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReportFolderPath {

    public static String getPathToReportFolder() {
        try {
            InputStream resourceAsStream = ReportFolderPath.class.getClassLoader().getResourceAsStream("my.properties");
            Properties prop = new Properties();
            prop.load(resourceAsStream);
            String reportFolder = prop.getProperty("sureFireDir");
            String dateFolder = prop.getProperty("build.time");
            return reportFolder + dateFolder + "/" + "screenshots" + "/";
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
        return null;
    }
}
