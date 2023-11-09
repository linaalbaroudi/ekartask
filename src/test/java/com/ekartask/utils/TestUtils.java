package com.ekartask.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// import com.fasterxml.jackson.core.type.TypeReference;
// import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TestUtils {

    public AppiumDriverLocalService service;

    AppiumDriver driver;
	WebDriverWait wait;

	public TestUtils(AppiumDriver driverr, WebDriverWait wait) {
		this.driver = driverr;
		this.wait = wait;
	}

    public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
        service = new AppiumServiceBuilder()
            .withAppiumJS(new File( "C:\\Users\\...\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .withIPAddress("127.0.0.1")
            .usingPort(4723)
            .withTimeout(Duration.ofSeconds(300))
            .build();

        service.start();
        return service;
    }

    public void waitForElementToAppear(By byElement, WebDriverWait wait) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
    }

    public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
        File source = driver.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports" + testCaseName + ".png";
        FileHandler.copy(source, new File(destinationFile));
        return destinationFile;
    }

}
