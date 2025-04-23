package org.app.utils;

import io.qameta.allure.Allure;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtil {
    private AllureUtil(){
        super();
    }
    public static final String ALLURE_REPORT_PATH="test-outputs/allure-result";

    public static void attachLogsToAllureReport(){
        try{
            File logFile;
            logFile = FilesUtil.getLatestFile(LogsUtil.LOGS_PATH);
            if (!logFile.exists()){
                LogsUtil.warn("Log file does not exist :" + LogsUtil.LOGS_PATH);
                return;
            }
            Allure.addAttachment("logs.log" , Files.readString((logFile.toPath())));
        } catch (Exception e){
            LogsUtil.error(("failed to attach logs to Allure Report: " + e.getMessage()));
        }


    }
    public static void attachScreenshot(String screenshotName, String screenShotPath){
        try {
            Allure.addAttachment(screenshotName, Files.newInputStream(Path.of(screenShotPath)));
        }catch (Exception e){
            LogsUtil.error("failed to attach screenshot to allure report: " + e.getMessage());
        }
    }
}
