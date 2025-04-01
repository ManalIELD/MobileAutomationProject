package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProductsPage {
    //variables
    AndroidDriver driver;

    //constructor
    public ProductsPage(AndroidDriver driver){
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null!");
        }
        this.driver = driver;
    }

    //locators
    private By cartIcon= AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/appbar_btn_cart\")");
    private By addToCartButton=AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
    private By addedToCart=AppiumBy.androidUIAutomator("new UiSelector().text(\"ADDED TO CART\")\n");
    //actions
    public void addProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }

    public void clickCartIcon() {
        driver.findElement(cartIcon).click();
    }

    //validations
    public void validateItemAddedToCart() {
        Assert.assertTrue(driver.findElement(addedToCart).isDisplayed(), "Item was not added to the cart!");
    }

}
