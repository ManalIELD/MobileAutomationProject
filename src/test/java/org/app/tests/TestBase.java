package org.app.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.app.utils.PropertyReader;
import org.app.utils.ScreenshotsUtil;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

public class TestBase {
    protected AndroidDriver driver; // This is the global driver instance
    private AppiumDriverLocalService service;
    private PropertyReader propertyReader;

    @BeforeClass
    public void setUpAppiumService() {
        service = new AppiumServiceBuilder()
               // .withAppiumJS(new File("C:/Users/Manal Ibrahim/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .usingPort(4723)
                .withIPAddress("127.0.0.1")
                .withArgument(() -> "--use-drivers", "uiautomator2")
                .build();
        service.start();
    }

    @BeforeMethod
    public void setUpDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                //.setDeviceName("Pixel_7_Pro_API_30")
                .setApp(System.getProperty("user.dir") + "/src/test/resources/General-Store.apk")
                .setAutomationName("uiautomator2")
                .setUdid("")

                .noReset(); // Keeps app data between sessions
        options.setCapability("appium:ignoreHiddenApiPolicyError", true);
        try {
            driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
            ScreenshotsUtil.setDriver(driver);
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    @AfterMethod
    public void tearDownDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterClass
    public void tearDownAppiumService() {
        if (service != null) {
            service.stop();
        }
    }
}
