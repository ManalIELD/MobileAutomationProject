package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.CartPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestNGListeners.class)
public class CartTests extends TestBase{

    //tests
    @Test(dependsOnMethods = "org.app.tests.ProductsTests.validAddingProductsAndProceedingToCartPage")
    public void validCartTest(){
        new CartPage(driver)
                .addItemValidation(nikeBlaze)
                .validateTotalPurchaseAmount()
                .selectEmailCheckbox()
                .clickPurchaseButton();
    }
}
