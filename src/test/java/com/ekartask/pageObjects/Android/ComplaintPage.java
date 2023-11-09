package com.ekartask.pageObjects.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.ekartask.utils.AndroidActions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ComplaintPage extends AndroidActions{
    AndroidDriver driver;
    WebDriverWait wait;

    // TODO: define in constants
    final String errorMessage = "Please choose back Image";

    public ComplaintPage(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[1]")
    private WebElement form;
    private By formBy = By.xpath("//android.widget.TextView[1]");

    @AndroidFindBy(id = "in.testdemo.map:id/front_img")
    private WebElement FrontImgInput;

    @AndroidFindBy(id = "in.testdemo.map:id/back_img")
    private WebElement BacktImgInput;

    @AndroidFindBy(id = "in.testdemo.map:id/left_img")
    private WebElement LeftImgInput;

    @AndroidFindBy(id = "in.testdemo.map:id/right_img")
    private WebElement RightImgInput;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/text1'][1]")
    private WebElement pickerOption;

    @AndroidFindBy(id = "in.testdemo.map:id/cmd_txt")
    private WebElement commentField;

    @AndroidFindBy(id = "in.testdemo.map:id/next_btn")
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//android.widget.Toast[1]")
    private WebElement toastView;

    // valid test scenarion - fill form with 4 images and a comment
    public MapPage fillFormComplete(String comment) {
        waitForElementToAppear(formBy, wait);

        pickImage(FrontImgInput, 1);
        pickImage(BacktImgInput, 2);
        pickImage(LeftImgInput, 3);
        pickImage(RightImgInput, 4);

        commentField.sendKeys(comment);

        scrollToText("NEXT");
        nextButton.click();

        return new MapPage(driver, wait);
    }

    // invalid test scenarion - fill form with Front Image only and a comment
    public String fillFormIncomplete(String comment) {
        waitForElementToAppear(formBy, wait);

        pickImage(FrontImgInput, 1);

        commentField.sendKeys(comment);

        scrollToText("NEXT");
        nextButton.click();

        return toastView.getText();
    }

    // invalid test scenarion - fill form with 4 Images only but without comment
    public void fillFormWithoutComment(SoftAssert softAssert) {
        waitForElementToAppear(formBy, wait);

        pickImage(FrontImgInput, 1);
        pickImage(BacktImgInput, 2);
        pickImage(LeftImgInput, 3);
        pickImage(RightImgInput, 4);

        scrollToText("NEXT");
        nextButton.click();
        softAssert.assertTrue(nextButton.isDisplayed(), "Complaint verification faild");
    }

    // helper method for using image picker
    private void pickImage(WebElement ImgInput, int index) {
        waitForElementToAppear(formBy, wait);
        ImgInput.click();
        pickerOption.click();
        driver.context("NATIVE_APP");
        WebElement imagesFolder = driver.findElement(AppiumBy.androidUIAutomator("text(\"Pictures\")"));
        imagesFolder.click();
        By img = AppiumBy
                .androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").index(" + index + ")");
        driver.findElement(img).click();
    }

    //TODO: define reusable methods

    // public WebElement getFrontImgInput() {
    // return FrontImgInput;
    // }

    // public void enterComment(String comment) {
    // commentField.sendKeys(comment);
    // }

    // public void submitComplaint() {
    // scrollToText("NEXT");
    // nextButton.click();
    // }

    // public String getToastMessage() {
    // return toastView.getText();
    // }
}
