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
public class E2eTest extends TestBase {

    //test

    @Test
    public void E2eScenario(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown();
        loginPage.chooseCountry(testData.getJsonData("login-credentials.person1.country"));
        loginPage.enterName(testData.getJsonData("login-credentials.person1.name"));
        loginPage.selectGender(testData.getJsonData("login-credentials.person1.gender"));
        loginPage.clickShopButton();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart(testData.getJsonData("product-names.Air-Jordan-4-Retro"));
        productsPage.addProductToCart(testData.getJsonData("product-names.Nike-Blazer-Mid-'77.name"));
        productsPage.addProductToCart(testData.getJsonData("product-names.Converse-All-Star.name"));
        productsPage.validateItemAddedToCart();
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.addItemValidation(testData.getJsonData("product-names.Nike-Blazer-Mid-'77.name"));
        //cartPage.validateTotalPurchaseAmount();
        cartPage.selectEmailCheckbox();
        cartPage.clickPurchaseButton();
    }
    @AfterClass
    public void afterClass(){
        AllureUtil.attachLogsToAllureReport();
    }
}
