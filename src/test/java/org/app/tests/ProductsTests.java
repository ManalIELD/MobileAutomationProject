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
    @Test(dependsOnMethods = "invalidNavigationToCart")
    public void validAddingProductsAndProceedingToCartPage(){
        new ProductsPage(driver).addProductToCart(testData.getJsonData("product-names.Air-Jordan-4-Retro"))
                .addProductToCart(testData.getJsonData("product-names.Nike-Blazer-Mid-'77.name"))
                .addProductToCart(testData.getJsonData("product-names.Converse-All-Star.name"))
                .validateItemAddedToCart()
                .clickCartIcon();
    }
}
