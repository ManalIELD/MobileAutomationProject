package org.app.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private static final int TIMEOUT = 10;  // Default timeout in seconds

    private WaitUtil() {
        // Private constructor to prevent instantiation
    }

    // Wait for element to be present
    public static WebElement waitForElementPresence(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        LogsUtil.info("Waiting for element to be present: " + locator.toString());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to be visible
    public static WebElement waitForElementVisibility(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        LogsUtil.info("Waiting for element to be visible: " + locator.toString());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public static WebElement waitForElementClickability(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        LogsUtil.info("Waiting for element to be clickable: " + locator.toString());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
}
