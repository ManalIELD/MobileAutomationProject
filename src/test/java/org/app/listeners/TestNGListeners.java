package org.app.listeners;

import org.app.utils.*;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements ITestListener, IInvokedMethodListener, IExecutionListener {

    File allure_results = new File("test-outputs/allure-results");
    File screenshots = new File("test-outputs/screenshots");
    File logs = new File("test-outputs/logs");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test execution started");
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
        if (method.isTestMethod()) {
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotsUtil.takeScreenshot("passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtil.takeScreenshot("failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtil.takeScreenshot("skipped-" + testResult.getName());
            }
            AllureUtil.attachLogsToAllureReport();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case " + result.getName() + " skipped");
    }
}
