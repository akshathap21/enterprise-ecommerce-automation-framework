package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import models.UserData;
import utilities.JsonReader;

public class UserProfileTest extends BaseTest{

	private String uniqueEmail = "sdet_expert_" + System.currentTimeMillis() + "@test.com";

	@Test(priority = 1, description = "Test Case 1: Register User with JSON Test Data")
	 public void verifyUserRegistrationWorkflow() {
        // Fetching the user data model instance cleanly
        UserData.UserProfile user = JsonReader.getValidUser();

        String successMsg = homePage.clickLoginMenu()
            .registerNewUserIntent(user.name, uniqueEmail)
            .fillAccountInformation(user.password, user.day, user.month, user.year)
            .fillAddressDetails(user.firstName, user.lastName, user.company, user.address, 
                                user.country, user.state, user.city, user.zipcode, user.mobile)
            .getAccountCreatedSuccessMessage();

        Assert.assertEquals(successMsg, "ACCOUNT CREATED!");
    }
}
