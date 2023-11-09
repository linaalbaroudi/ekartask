package com.ekartask.utils;

import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends TestUtils{
	
	AndroidDriver driver;
	
	public AndroidActions(AndroidDriver driver, WebDriverWait wait)
	{
		super(driver, wait);
		this.driver = driver;
	}

	// helper method for scrolling to next button
    public void scrollToText(String text) {
        driver.findElement(
                AppiumBy.androidUIAutomator(
                        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"));"));
    }
}
