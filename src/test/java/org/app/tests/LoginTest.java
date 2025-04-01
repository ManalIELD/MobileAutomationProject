package org.app.tests;

import io.appium.java_client.android.AndroidDriver;
import org.app.pages.CartPage;
import org.app.pages.LoginPage;
import org.app.pages.ProductsPage;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    //variables

    //test
    @Test
    public void login(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown();
        loginPage.chooseCountry("Argentina");
        loginPage.enterName("Manal");
        loginPage.selectFemaleGender();
        loginPage.clickShopButton();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart();
        productsPage.validateItemAddedToCart();
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.addItemValidation();
        //cartPage.validateTotalPurchaseAmount();
        cartPage.selectEmailCheckbox();
        cartPage.clickPurchaseButton();
    }
}
