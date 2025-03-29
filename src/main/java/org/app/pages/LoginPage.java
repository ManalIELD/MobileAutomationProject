package org.app.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class LoginPage {

    //variables
    AndroidDriver driver;
    //constructor
    public LoginPage(AndroidDriver driver){
        this.driver=driver;
    }

    //locators
    private By countryDropdown = AppiumBy.id("android:id/text1");
    /*private By countrySelection =AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"Egypt\"))");*/
    private By nameField = AppiumBy.className("android.widget.EditText");
    private By genderFemale = AppiumBy.xpath("//android.widget.RadioButton[@text='Female']");
    private By shopButton = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnLetsShop\")");


    //actions
    // Clicks the dropdown to open country list
    public void clickCountryDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(countryDropdown)).click();
    }

    // Selects a specific country by scrolling
    public void chooseCountry(String countryName) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"" + countryName + "\"))")).click();
    }

    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    // Select Female Gender
    public void selectFemaleGender() {
        driver.findElement(genderFemale).click();
    }

    // Click the Shop Button
    public void clickShopButton() {
        driver.findElement(shopButton).click();
    }

}
