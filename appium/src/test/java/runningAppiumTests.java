import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.hc.core5.util.Timeout;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class runningAppiumTests {

    @Test
    public void testRunServer() throws InterruptedException {


        AppiumDriverLocalService service ;
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/Manal Ibrahim/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .usingPort(4723)
                        .withIPAddress("127.0.0.1")
                                .withArgument(()-> "--use-drivers","uiautomator2").build();
        service.start();
        Thread.sleep(5000);
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel_7_Pro_API_30") // Make sure emulator name is correct
                .setApp(System.getProperty("user.dir") + "/src/main/resources/General-Store.apk") // Ensure app path is correct
                .setAutomationName("uiautomator2")
                .setUdid("emulator-5554")
                .noReset(); // Keeps app data between sessions

        AndroidDriver driver;




            try {
                driver = new AndroidDriver(
                        // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                        new URI("http://127.0.0.1:4723").toURL(), options
                );System.out.println("App launched successfully!");


        }catch (MalformedURLException | URISyntaxException e)
            {
                throw new RuntimeException(e);
            }
        Thread.sleep(5000);

        WebElement countryDropdown = driver.findElement(AppiumBy.id("android:id/text1"));
        countryDropdown.click();
        Thread.sleep(2000);

        // country selection
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                        ".scrollIntoView(new UiSelector().text(\"Egypt\"))"
        )).click();
        WebElement nameField = driver.findElement(AppiumBy.className("android.widget.EditText"));
        nameField.sendKeys("Manal");

        // Select Gender
        WebElement genderFemale = driver.findElement(AppiumBy.xpath("//android.widget.RadioButton[@text='Female']"));
        genderFemale.click();

        // Click "Let's Shop" Button
        WebElement shopButton = driver.findElement(AppiumBy.xpath("//android.widget.SubmitButton[@text='Let's Shop']"));
        shopButton.click();
        Thread.sleep(5000);
        driver.quit();
            service.stop();
    }
}
