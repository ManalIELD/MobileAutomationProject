package org.app.listeners;

import org.app.utils.FilesUtil;
import org.app.utils.LogsUtil;
import org.app.utils.PropertyReader;
import org.app.utils.ScreenshotsUtil;
import org.app.utils.AllureUtil;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements ITestListener, IInvokedMethodListener, IExecutionListener {

    File allure_results = new File("test-outputs/allure-results");
    File screenshots = new File("test-outputs/screenshots");
    File logs = new File("test-outputs/logs");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test execution started");
        PropertyReader propertyReader = new PropertyReader();
        FilesUtil.deleteFiles(allure_results);
        FilesUtil.deleteFiles(screenshots);
        FilesUtil.cleanDirectory(logs);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test execution finished");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        switch (testResult.getStatus()){
            case ITestResult.SUCCESS -> ScreenshotsUtil.takeScreenshot("passed-"+testResult.getName());
            case ITestResult.FAILURE -> ScreenshotsUtil.takeScreenshot("failed-"+testResult.getName());
            case ITestResult.SKIP -> ScreenshotsUtil.takeScreenshot("skipped-"+testResult.getName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " passed");
        AllureUtil.attachLogsToAllureReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " skipped");
        AllureUtil.attachLogsToAllureReport();
    }
}
