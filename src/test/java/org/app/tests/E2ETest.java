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
public class E2ETest extends TestBase {

    //test

    @Test
    public void E2eScenario() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown()
                .chooseCountry(testData.getJsonData("login-credentials.person1.country"))
                .enterName(testData.getJsonData("login-credentials.person1.name"))
                .selectGender(testData.getJsonData("login-credentials.person1.gender"))
                .clickShopButton()

                .addProductToCart(testData.getJsonData("product-names.Air-Jordan-4-Retro"))
                .addProductToCart(testData.getJsonData("product-names.Nike-Blazer-Mid-'77.name"))
                .addProductToCart(testData.getJsonData("product-names.Converse-All-Star.name"))
                .validateItemAddedToCart()
                .clickCartIcon()

                .addItemValidation(testData.getJsonData("product-names.Nike-Blazer-Mid-'77.name"))
                //.validateTotalPurchaseAmount();
                .validateTotalPurchaseAmount2()
                .selectEmailCheckbox()
                .clickPurchaseButton();

    }
}
