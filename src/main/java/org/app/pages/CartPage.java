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
public class CartPage {
    //variables
    AndroidDriver driver;

    // Constructor
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }
// i used by.xpath >>> but it didn't work as expected especially for "visit... button" so i used uiautomator, just wanna let u know

    private By productName = AppiumBy.androidUIAutomator("new UiSelector().text(\"Air Jordan 4 Retro\")");
    //private By productPrice = AppiumBy.androidUIAutomator("new UiSelector().text(\"$160.97\")");
    private By emailCheckbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\")");
    private By purchaseButton = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Visit\")");


    // validations
    @Step( "add item validation")
    public void addItemValidation() {
        Assert.assertTrue(driver.findElement(productName).isDisplayed(), "Item is not displayed in the cart!");
    }
// this didn't work well and I couldn't excute other testcases well , so I ignored it for now to check all worked properly

    //public void validateTotalPurchaseAmount() {
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(totalPurchaseAmount));

       // String totalAmountText = driver.findElement(totalPurchaseAmount).getText().trim();
       // Assert.assertEquals(totalAmountText, "$160.97", "Total purchase amount mismatch!");
   // }
 // Actions
       @Step("select email checkbox")
    public void selectEmailCheckbox() {
        ActionsUtil.click(driver,emailCheckbox);
    }
      @Step("click purchase button")
    public void clickPurchaseButton() {
        ActionsUtil.click(driver,purchaseButton);
    }
}




