package com.ekartask;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ekartask.pageObjects.Android.ComplaintPage;
import com.ekartask.pageObjects.Android.MapPage;
import io.appium.java_client.AppiumBy;

public class ComplaintTest extends Base {

    String acceptedMessage = "Complaint Accepted";
    String errorMessage = "Please choose back Image";
    String comment = "this is a test comment";

    @BeforeClass
    public static void BeforeClass() throws Exception {
        // verify location permiossion granted
        try {
            By element = AppiumBy
                    .accessibilityId("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test ()
    public void ValidComplaint() throws Exception {
        MapPage mapPage = new MapPage(driver, wait);

        // 1- As a user, I should be able to locate my location via pin drop.
        ComplaintPage complaintPage = mapPage.selectLocation();

        // 2- As a user, I should upload four images to the app after clicking on the pin drop.
        mapPage = complaintPage.fillFormComplete(comment);
        mapPage.verifyComplaintAccepted(softAssert);
    }

    @Test
    public void ComplaintWithMissingImages() throws Exception {
        MapPage mapPage = new MapPage(driver, wait);

        // locate my location via pin drop
        ComplaintPage complaintPage = mapPage.selectLocation();

        //3- I should not be able to proceed to the next step until I have uploaded four images.
        String snackbarMessage = complaintPage.fillFormIncomplete(comment);
        softAssert.assertEquals(snackbarMessage, errorMessage, "Complaint verification faild");
    }

    @Test
    public void ComplaintWithoutComment() {
        MapPage mapPage = new MapPage(driver, wait);

        // locate my location via pin drop
        ComplaintPage complaintPage = mapPage.selectLocation();
        
        //4- User should not be able to proceed to the next step until entering a comment.
        complaintPage.fillFormWithoutComment(softAssert);
    }

    // @DataProvider (name = "test_data")
    // public Object[][] getData(){
    //     return new Object[][] {
    //         {"this is a test for valid scenario"}
    //     };
    // }

}
