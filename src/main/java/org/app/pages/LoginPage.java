package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.app.utils.ActionsUtil;
import org.app.utils.CustomSoftAssertionUtil;
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
    public LoginPage clickCountryDropdown() {
        ActionsUtil.click(driver, countryDropdown);  // Using the click method from ActionsUtil
        return this;
    }

    // Selects a specific country by scrolling and clicking on it
    @Step("choose country")
    public LoginPage chooseCountry(String countryName) {
        ActionsUtil.scrollToElement(driver, countryName);
        By countryLocator = By.xpath("//android.widget.TextView[@text='" + countryName + "']");
        ActionsUtil.click(driver, countryLocator);
        return this;
    }

    // Enter name into the name field
    @Step("enter name")
    public LoginPage enterName(String name) {
        ActionsUtil.type(driver, nameField, name);  // Using the type method from ActionsUtil
        return this;
    }

    // Select Gender
    @Step ("select gender")
    public LoginPage selectGender(String gender) {
        By genderLocator = By.xpath("//android.widget.RadioButton[@text='" + gender + "']");
        ActionsUtil.click(driver, genderLocator);
        return this;
    }


    // Click the Shop Button
    @Step("click shop button")
    public ProductsPage clickShopButton() {
        ActionsUtil.click(driver, shopButton) ;
        return new ProductsPage(driver);
    }

    //validations
    public ProductsPage validateLoginPage(){
        String actualTitle = driver.getTitle();
        //ActionsUtil.getText(driver,loginPageTitle);
        String expectedTitle = "General Store";
        CustomSoftAssertionUtil.softAssertion.assertEquals(actualTitle,expectedTitle,"The page title does not match. You may not be on the login page.");
        return new ProductsPage(driver);
    }
}
