package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
import org.app.utils.CustomSoftAssertionUtil;
import org.openqa.selenium.By;
import org.testng.Assert;


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
    private By loginPageTitle = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/toolbar_title\")\n");

    //actions
    @Step("add product to cart")
    public ProductsPage addProductToCart(String productName) {
        By product = AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
                    ".scrollIntoView(new UiSelector().textContains(\"" + productName + "\"))");
        ActionsUtil.findElement(driver,product);
        //ActionsUtil.scrollToElement(driver,productName);
        ActionsUtil.click(driver,addToCartButton);
        return this;
    }

    @Step("click cart icon")
    public CartPage clickCartIcon() {

        ActionsUtil.click(driver,cartIcon);
        return new CartPage(driver);
    }

    //validations
    @Step("Validating item added to the cart")
    public ProductsPage validateItemAddedToCart() {
        Assert.assertTrue(driver.findElement(addedToCart).isDisplayed(), "Item was not added to the cart!");
        return this;
    }

    public ProductsPage validateLoginPage(){
        String actualTitle = ActionsUtil.getText(driver,loginPageTitle);
        String expectedTitle = "General Store";
        CustomSoftAssertionUtil.softAssertion.assertEquals(actualTitle,expectedTitle,"The page title does not match. You may not be on the login page.");
        return new ProductsPage(driver);
    }


}
