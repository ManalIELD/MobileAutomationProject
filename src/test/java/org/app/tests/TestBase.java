package org.app.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.app.utils.JsonUtil;
import org.app.utils.PropertiesUtil;
import org.app.utils.ScreenshotsUtil;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.app.utils.PropertiesUtil.loadProperties;

public class TestBase {
    //variables
    protected AndroidDriver driver; // This is the global driver instance
    private AppiumDriverLocalService service;
    JsonUtil testData;

    @BeforeSuite
    public void beforeSuite(){
        loadProperties();
        testData = new JsonUtil("test-data");
    }

    @BeforeClass
    public void setUpAppiumService() {
        service = new AppiumServiceBuilder()
               // .withAppiumJS(new File("C:/Users/Manal Ibrahim/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .usingPort(Integer.parseInt(PropertiesUtil.getPropertyValue("port")))
                .withIPAddress(PropertiesUtil.getPropertyValue("ipAddress"))
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
                .setAutomationName(PropertiesUtil.getPropertyValue("driver"))
                .setUdid(PropertiesUtil.getPropertyValue("deviceId"))
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
