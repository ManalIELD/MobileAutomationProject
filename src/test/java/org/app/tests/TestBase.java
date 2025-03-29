package org.app.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.app.utils.PropertyReader;
import org.openqa.selenium.Platform;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestBase {
    protected AndroidDriver driver;
    private AppiumDriverLocalService service;
    private PropertyReader propertyReader;

    @BeforeClass
    public void setUpAppiumService() {
        propertyReader = new PropertyReader();
        service = new AppiumServiceBuilder()
                .withIPAddress(propertyReader.getProperty("ipAddress")) // Set the address
                .usingPort(Integer.parseInt(propertyReader.getProperty("port")))// Set the port
                .withArgument(() -> "--use-drivers", propertyReader.getProperty("driver")) //To Run Appium with specific driver
                .build();
        service.start();
    }

    //Set up clean driver for each test
    @BeforeMethod
    public void setUpDriver() {
        UiAutomator2Options options = new UiAutomator2Options() //To add UiAutomator Capabilities (Options)
                .setDeviceName(propertyReader.getProperty("device")) //Running Device Name(e.g. like the name from Android Studio)
                .noReset() //To make appium not start with clean state (e.g. doesn't reinstall the app every run)
                .setPlatformName(Platform.ANDROID.name()) //Platform name
                .setUdid("afbb072a")
                .setApp("E:\\ITI\\Appium\\AppiumTeamProject\\src\\test\\resources\\General-Store.apk"); //Path of the App (ApiDemos-debug.apk)
                // Ignore hidden API policy error
                 options.setCapability("appium:ignoreHiddenApiPolicyError", true);
        try {
            driver = new AndroidDriver(
                    new URI("http://"
                            + propertyReader.getProperty("ipAddress") + ":" //Must be the same Address Appium is running on
                            + propertyReader.getProperty("port")).toURL(), options);//Must be the same port Appium is running on
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    //Close the Running Driver after every test finishes
    @AfterMethod
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    //Close the Running Service after all tests finish
    @AfterClass
    public void tearDownAppiumService() {
        if (service != null) {
            service.stop();
        }
    }
}
