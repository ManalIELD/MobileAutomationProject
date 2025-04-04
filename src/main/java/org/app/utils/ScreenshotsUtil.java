package org.app.utils;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenshotsUtil {
    public static final String SCREENSHOT_PATH="test-outputs/screenshots/";
    private ScreenshotsUtil(){
        super();
    }

    public static void takeScreenshot(AndroidDriver driver, String screenshotName){
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(SCREENSHOT_PATH + screenshotName + ".png");
            FileUtils.copyFile(screenshot,screenshotFile);
            AllureUtil.attachScreenshot(screenshotName,screenshotFile.getPath());
        } catch (Exception e) {
            LogsUtil.error("failed to take a screenshot"+ e.getMessage());
        }
    }
}
