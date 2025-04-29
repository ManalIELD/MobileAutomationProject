package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestNGListeners.class)
public class E2ETest extends TestBase  {

    //test

    @Test
    public void E2eScenario() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown()
                .chooseCountry(country)
                .enterName(name)
                .selectGender(gender)
                .clickShopButton()

                .addProductToCart(airJordan)
                .addProductToCart(nikeBlaze)
                .addProductToCart(converse)
                .validateItemAddedToCart()
                .clickCartIcon()

                .addItemValidation(nikeBlaze)
                .validateTotalPurchaseAmount()
                .selectEmailCheckbox()
                .clickPurchaseButton();

    }
}
