package com.ekartask.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends TestUtils {

    IOSDriver driver;

    public IOSActions(IOSDriver driver,  WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
    }

    // helper method for scrolling to next button
    public void scrollToWebElement(WebElement ele) {
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", ((RemoteWebElement) ele).getId());

        driver.executeScript("mobile:scroll", params);
    }
}
