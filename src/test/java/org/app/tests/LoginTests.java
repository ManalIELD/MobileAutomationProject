package org.app.tests;

import org.app.listeners.TestNGListeners;
import org.app.pages.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestNGListeners.class)
public class LoginTests extends TestBase{

    //tests
    @Test
    public void invalidLogin(){
        new LoginPage(driver).clickCountryDropdown()
                .chooseCountry(country)
                .selectGender(gender)
                .clickShopButton()
                .validateLoginPage();

    }
    @Test(priority = 1)
    public void validLogin(){
        new LoginPage(driver).clickCountryDropdown()
                .chooseCountry(country)
                .enterName(name)
                .selectGender(gender)
                .clickShopButton();
    }

}
