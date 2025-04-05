package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.CartPage;
import org.app.pages.LoginPage;
import org.app.pages.ProductsPage;
import org.app.utils.AllureUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestNGListeners.class)
public class LoginTest extends TestBase {
    //variables

    //test
    @Test
    public void login(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown();
        loginPage.chooseCountry("Argentina");
        loginPage.enterName("Manal");
        loginPage.selectGender("Female");
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
    @AfterClass
    public void afterClass(){
        AllureUtil.attachLogsToAllureReport();
    }
}
