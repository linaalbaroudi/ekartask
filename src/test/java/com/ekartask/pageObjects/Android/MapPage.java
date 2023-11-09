package com.ekartask.pageObjects.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.ekartask.utils.AndroidActions;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class MapPage extends AndroidActions{

	AndroidDriver driver;
	WebDriverWait wait;
	final private String acceptedMessage = "Complaint Accepted";

	public MapPage(AndroidDriver driverr, WebDriverWait wait) {
		super(driverr, wait);
		this.driver = driverr;
		this.wait = wait;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.view.View")
	private WebElement pin;
	private By pinBy = By.xpath("//android.view.View");

	@AndroidFindBy(xpath = "//android.widget.Toast[1]")
	private WebElement toastView;

	// locate my location via pin drop.
	public ComplaintPage selectLocation() {
		waitForElementToAppear(pinBy, wait);
		Assert.isTrue(pin.isDisplayed(), "pin is not displayed");
		pin.click();
		return new ComplaintPage(driver, wait);
	}

	// 	verify Complaint Accepted
	public void verifyComplaintAccepted(SoftAssert softAssert) {
		waitForElementToAppear(pinBy, wait);
		String snackbarMessage = toastView.getText();
		softAssert.assertEquals(snackbarMessage, acceptedMessage, "Complaint faild");
	}
}
