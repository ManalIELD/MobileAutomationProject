package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
import org.app.utils.CustomSoftAssertionUtil;
import org.app.utils.WaitUtil;
import org.app.utils.LogsUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.util.List;

public class CartPage {
    //variables
    AndroidDriver driver;

    // Constructor
    public CartPage(AndroidDriver driver) {
        this.driver = driver;
    }

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


    @Step("validate total purchase amount")
    public CartPage validateTotalPurchaseAmount() {
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
    @Step("Validate we are on the Products page")
    public CartPage validateProductsPage(){
        String actualTitle = ActionsUtil.getText(driver,ProductsPageTitle);
        String expectedTitle = "Products";
        CustomSoftAssertionUtil.softAssertion.assertEquals(actualTitle,expectedTitle,"The page title does not match. You may not be on the login page.");
        return new CartPage(driver);
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

}




