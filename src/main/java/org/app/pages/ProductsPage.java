package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
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
        this.driver = driver;
    }

    //locators
    private By cartIcon= AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/appbar_btn_cart\")");
    private By addToCartButton=AppiumBy.xpath("//android.widget.TextView[@text='ADD TO CART']");
    private By addedToCart=AppiumBy.androidUIAutomator("new UiSelector().text(\"ADDED TO CART\")\n");
    //actions
    @Step("add product to cart")
    public void addProductToCart() {
        ActionsUtil.click(driver,addToCartButton);
    }
    @Step("click cart icon")
    public void clickCartIcon() {
        ActionsUtil.click(driver,cartIcon);
    }

    //validations
    public void validateItemAddedToCart() {
        Assert.assertTrue(driver.findElement(addedToCart).isDisplayed(), "Item was not added to the cart!");
    }

}
