package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.CartPage;
import org.app.pages.LoginPage;
import org.app.pages.ProductsPage;
import org.app.utils.AllureUtil;
import org.app.utils.FilesUtil;
import org.app.utils.JsonUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;

import static org.app.utils.PropertiesUtil.loadProperties;

@Listeners(TestNGListeners.class)
public class LoginTest extends TestBase {
    //variables
File allure_results = new File("test-outputs/allure-results");
JsonUtil testData;
    //test
    @BeforeSuite
    public void beforeSuite(){
        loadProperties();
        FilesUtil.deleteFiles(allure_results);
        testData = new JsonUtil("test-data");
    }
    @Test
    public void login(){
        LoginPage loginPage = new LoginPage(driver);

        loginPage.clickCountryDropdown();
        loginPage.chooseCountry(testData.getJsonData("login-credentials.Country"));
        loginPage.enterName("login-credentials.name");
        loginPage.selectGender("login-credentials.gender");
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
