package org.app.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class ActionsUtil {
    private ActionsUtil(){

    }
    public static void type(AndroidDriver driver, By locator, String data) {
        WebElement element = WaitUtil.waitForElementVisibility(driver, locator);
        element.sendKeys(data);
        LogsUtil.info("Data entered", data, "in the field", locator.toString());
    }

    public static void click(AndroidDriver driver, By locator) {
        WebElement element = WaitUtil.waitForElementClickability(driver, locator);
        element.click();
        LogsUtil.info("Clicked on element", locator.toString());
    }

    public static WebElement findElement(AndroidDriver driver, By locator){
        LogsUtil.info("finding element", locator.toString());
        return driver.findElement(locator);
    }

    public static String getText(AndroidDriver driver, By locator) {
        WebElement element = WaitUtil.waitForElementVisibility(driver, locator);
        String text = element.getText();
        LogsUtil.info("Getting text from", locator.toString(), "text:", text);
        return text;
    }

    public static void scrollToElement(AndroidDriver driver, String textToFind) {
        try {
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                            ".scrollIntoView(new UiSelector().text(\"" + textToFind + "\"))"));
            LogsUtil.info("Scrolled to element", textToFind);
        } catch (Exception e) {
            LogsUtil.error("Failed to scroll to element", textToFind);
        }
    }
    public static void scrollToBottom(AndroidDriver driver) {
        try {
            // This UIAutomator command will "fling" (scroll fast) to the end of the first scrollable element found.
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).flingToEnd(5)"
            ));
            LogsUtil.info("Scrolled to bottom of the page successfully.");
        } catch (Exception e) {
            LogsUtil.error("Failed to scroll to bottom of the page: " + e.getMessage());
        }
    }


}
