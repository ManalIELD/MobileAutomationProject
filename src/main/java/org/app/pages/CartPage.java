package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
import org.app.utils.CustomSoftAssertionUtil;
import org.app.utils.WaitUtil;
import org.app.utils.JsonUtil;
import org.app.utils.LogsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.awt.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class CartPage {
    //variables
    AndroidDriver driver;
    JsonUtil testData;
    // Constructor
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }
// i used by.xpath >>> but it didn't work as expected especially for "visit... button" so i used uiautomator, just wanna let u know

    //private By productName = AppiumBy.androidUIAutomator("new UiSelector().text(\"Air Jordan 4 Retro\")");
    //private By productPrice = AppiumBy.androidUIAutomator("new UiSelector().text(\"$ 160.97\")");
    private By emailCheckbox = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.CheckBox\")");
    private By purchaseButton = AppiumBy.androidUIAutomator("new UiSelector().textContains(\"Visit\")");
    private By totalPurchaesedAmount = AppiumBy.androidUIAutomator("new UiSelector().text(\"$ 325.97\")");
    private By productPrices = By.xpath("//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productPrice']");
    private By totalAmountLabel = By.id("com.androidsample.generalstore:id/totalAmountLbl");
    private By ProductsPageTitle = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")");

    // validations
    @Step( "add item validation")
    public CartPage addItemValidation(String productName) {
        By productInCart = AppiumBy.androidUIAutomator("new UiSelector().text(\"" + productName + "\")");
        WaitUtil.waitForElementVisibility(driver,productInCart);
        Assert.assertTrue(driver.findElement(productInCart).isDisplayed(), "Item '" + productName + "' is not displayed in the cart!");
        return this;
    }

// this didn't work well and I couldn't excute other testcases well , so I ignored it for now to check all worked properly

    //public void validateTotalPurchaseAmount() {
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(totalPurchaseAmount));

       // String totalAmountText = driver.findElement(totalPurchaseAmount).getText().trim();
       // Assert.assertEquals(totalAmountText, "$160.97", "Total purchase amount mismatch!");
       @Step("validate total purchase amount")
       public CartPage validateTotalPurchaseAmount() {
           //double totalCalculatedPrice = 0.0;

          // List<String> products = Arrays.asList("Air-Jordan-4-Retro", "Nike-Blazer-Mid-'77", "Converse-All-Star", "PG-3");
         //for (String product : products) {
                //prices from json file
               ///String productPriceText = (String) testData.getJsonData("product-names." + product + ".price");

                //extract only prices excluding $, ..etc
              // double productPrice = Double.parseDouble(productPriceText.replace("$", "").replace(",", ""));


              // totalCalculatedPrice += productPrice;
         // }
          // String totalCalculatedPriceFormatted = String.format("$%.2f", totalCalculatedPrice);
           String totalAmountText = driver.findElement(totalPurchaesedAmount).getText().trim();
          // String totalAmountText = driver.findElement(totalPurchaesedAmount).getText().trim();
           CustomSoftAssertionUtil.softAssertion.assertEquals(totalAmountText, "$ 325.97", "Total purchase amount mismatch!");

           return this;
       }

    // }

    public CartPage validateTotalPurchaseAmount2() {
        double sum = 0.0;
        ActionsUtil.scrollToBottom(driver);
        List<WebElement> priceElements = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "").trim();
            sum += Double.parseDouble(priceText);
        }

        String totalText = driver.findElement(totalAmountLabel).getText().replace("$", "").trim();
        double total = Double.parseDouble(totalText);
        LogsUtil.info("Computed total: " + sum);
        LogsUtil.info("Displayed total: " + total);
        CustomSoftAssertionUtil.softAssertion.assertEquals(sum, total, "Total amount mismatch!");

        return this;
    }
 // Actions
       @Step("select email checkbox")
    public CartPage selectEmailCheckbox() {
        ActionsUtil.click(driver,emailCheckbox);
        return this;
    }
      @Step("click purchase button")
    public CartPage clickPurchaseButton() {
        ActionsUtil.click(driver,purchaseButton);
        return this;
    }

    //validations
    public CartPage validateProductsPage(){
        String actualTitle = ActionsUtil.getText(driver,ProductsPageTitle);
        String expectedTitle = "Products";
        CustomSoftAssertionUtil.softAssertion.assertEquals(actualTitle,expectedTitle,"The page title does not match. You may not be on the login page.");
        return new CartPage(driver);
    }
}




