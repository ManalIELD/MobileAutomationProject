package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.ProductsPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(TestNGListeners.class)
public class ProductsTests extends TestBase{

    //tests
    @Test(dependsOnMethods = "org.app.tests.LoginTests.validLogin")
    public void invalidNavigationToCart(){
        new ProductsPage(driver).clickCartIcon()
                .validateProductsPage();
    }
    @Test(dependsOnMethods = "org.app.tests.LoginTests.validLogin",priority = 1)
    public void validAddingProductsAndProceedingToCartPage(){
        new ProductsPage(driver)
                .addProductToCart(airJordan)
                .addProductToCart(nikeBlaze)
                .addProductToCart(converse)
                .validateItemAddedToCart()
                .clickCartIcon();
    }
}
