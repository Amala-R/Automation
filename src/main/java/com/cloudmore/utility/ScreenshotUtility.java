package com.cloudmore.utility;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.cloudmore.utility.Constants.USER_DIR;

public class ScreenshotUtility extends DriverManager{

    static Map<String, Object> mobileEmulation = new HashMap<>();

    public static void takeScreenshotForMobileSize() throws IOException {
        mobileEmulation.put("deviceName", "iPhone X");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        File mobileScreenshot = ((TakesScreenshot) getWebDriverInstance()).getScreenshotAs(OutputType.FILE);
        File screenshotFilePath = new File(System.getProperty(USER_DIR) + "/target/output/MobileScreenshot.png");
        FileUtils.copyFile(mobileScreenshot, screenshotFilePath);
        ReportUtility.getTest().info("Screenshot in the path : "+screenshotFilePath, MediaEntityBuilder.createScreenCaptureFromPath(String.valueOf(screenshotFilePath)).build());
    }

    public static void takeScreenshotForDesktopSize() throws IOException {
        File desktopScreenshot = ((TakesScreenshot) getWebDriverInstance()).getScreenshotAs(OutputType.FILE);
        File screenshotFilePath = new File(System.getProperty(USER_DIR) + "/target/output/DesktopScreenshot.png");
        FileUtils.copyFile(desktopScreenshot, screenshotFilePath);
        ReportUtility.getTest().info("Screenshot in the path : "+screenshotFilePath, MediaEntityBuilder.createScreenCaptureFromPath(String.valueOf(screenshotFilePath)).build());
    }

}