package com.ekartask;

import java.io.File;
import java.net.URL;
import java.time.Duration;

import org.junit.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {

    public static AndroidDriver driver;
	public static AppiumDriverLocalService service;
    public static WebDriverWait wait ;
    public static SoftAssert softAssert;
	
	@BeforeClass
	public static void ConfigureAppium() throws Exception
	{
        // statrt appium server
        service = new AppiumServiceBuilder()
            .withAppiumJS(new File( "C:\\Users\\...\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .withTimeout(Duration.ofSeconds(300))
            .build();

        service.start();

        // define driver capabilities 
        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setDeviceName("Pixel_3a_API_34_extension_level_7_x86_64");
        capabilities.setApp( System.getProperty("user.dir") + "\\src\\test\\java\\com\\ekartask\\assets\\ekar-app-demo.apk"); 
        capabilities.setCapability("autoGrantPermissions", true);
        
        // initialize driver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        // initialize explicit wait 
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // setup implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Initialize SoftAssert 
        softAssert = new SoftAssert();
	}
		
	@AfterClass
	public static void tearDown()
	{        
        softAssert.assertAll();
		driver.quit();
        service.stop();
	}
    
}
