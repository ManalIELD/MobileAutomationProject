package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
import org.app.utils.ScreenshotsUtil;
import org.openqa.selenium.By;


public class LoginPage {

    //variables
    AndroidDriver driver;
    //constructor
    public LoginPage(AndroidDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Driver instance cannot be null!");
        }
        this.driver = driver;
    }


    //locators
    private By countryDropdown = AppiumBy.xpath("//*[@resource-id='android:id/text1']");
    private By nameField = AppiumBy.className("android.widget.EditText");
    private By shopButton = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnLetsShop\")");


    //actions
    @Step("click country dropdown")
    public void clickCountryDropdown() {
        ActionsUtil.click(driver, countryDropdown);  // Using the click method from ActionsUtil
    }

    // Selects a specific country by scrolling and clicking on it
    @Step("choose country")
    public void chooseCountry(String countryName) {
        By countryLocator = By.xpath("//android.widget.TextView[@text='" + countryName + "']");
        ActionsUtil.scrollToElement(driver, countryLocator);
        ActionsUtil.click(driver, countryLocator);
    }

    // Enter name into the name field
    @Step("enter name")
    public void enterName(String name) {
        ActionsUtil.type(driver, nameField, name);  // Using the type method from ActionsUtil
    }

    // Select Gender
    @Step ("select gender")
    public void selectGender(String gender) {
        By genderLocator = By.xpath("//android.widget.RadioButton[@text='" + gender + "']");
        ActionsUtil.click(driver, genderLocator);
    }


    // Click the Shop Button
    @Step("click shop button")
    public void clickShopButton() {
        ActionsUtil.click(driver, shopButton) ; // Using the click method from ActionsUtil

     ScreenshotsUtil.takeScreenshot(driver, "shop button clicked");
                }
}
