package ru.netology;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.ru.netology.ru.netology.screen.MainScreen;

public class AppiumTest {

    private AppiumDriver driver;
    MainScreen mainScreen;


    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Xiaomi");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        mainScreen = new MainScreen(driver);

    }


    @Test
    public void emptyRowTest() {
        mainScreen.textToChange.isDisplayed();
        String expected = mainScreen.textToChange.getText();
        mainScreen.userInput.click();
        mainScreen.userInput.sendKeys("   ");
        mainScreen.changeButton.click();
        mainScreen.textToChange.isDisplayed();
        String actual = mainScreen.textToChange.getText();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void newActivityTest() {
        String inputText = "12345";
        mainScreen.userInput.sendKeys(inputText);
        mainScreen.buttonActivity.click();
        String actual = mainScreen.text.getText();
        Assertions.assertEquals("12345", actual);


    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

