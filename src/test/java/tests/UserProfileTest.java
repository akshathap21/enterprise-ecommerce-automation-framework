package tests;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import models.UserData;
import pages.HomePage;
import pages.LoginPage;
import pages.SignupPage;
import utilities.JsonReader;

public class UserProfileTest extends BaseTest{

	//private String uniqueEmail = "sdet_expert_" + System.currentTimeMillis() + "@test.com";

	@Test(priority = 1, description = "Test Case 1: Register User with JSON Test Data")
	 public void verifyUserRegistrationWorkflow(ITestContext context) {
        // Fetching the user data model instance cleanly
        UserData.UserProfile user = JsonReader.getValidUser();

     // Generate an isolated, unique runtime string inside this specific execution thread
        String threadSafeEmail = "sdet_expert_" + System.currentTimeMillis() + "@test.com";
        
        // Store it inside the TestNG Context map associated with this running thread instance
        context.setAttribute("sharedEmail", threadSafeEmail);
        
        String successMsg = homePage.clickLoginMenu()
            .registerNewUserIntent(user.name, threadSafeEmail)
            .fillAccountInformation(user.password, user.day, user.month, user.year)
            .fillAddressDetails(user.firstName, user.lastName, user.company, user.address, 
                                user.country, user.state, user.city, user.zipcode, user.mobile)
            .getAccountCreatedSuccessMessage();

        Assert.assertEquals(successMsg, "ACCOUNT CREATED!");
    }
	
	@Test(priority = 2, description = "Test Case 2: Login User with correct credentials")
    public void verifyValidLoginWorkflow(ITestContext context) {
        UserData.UserProfile user = JsonReader.getValidUser();
        
        // Safely extract the matching email value belonging to this execution thread context
        String threadSafeEmail = (String) context.getAttribute("sharedEmail");

        boolean isProfileHeaderVisible = homePage.clickLoginMenu()
            .login(threadSafeEmail, user.password)
            .isUserLoggedInMessageDisplayed();

        Assert.assertTrue(isProfileHeaderVisible, "Error: Profile identity header verification failure.");
    }
	
	
	 @Test(priority = 3, description = "Test Case 3: Login User with incorrect email and password")
	    public void verifyInvalidLoginResponse() {
	        UserData.InvalidProfile invalidUser = JsonReader.getInvalidUser();
	        
	     // 1. Open the login interface completely first
	        LoginPage loginPage = homePage.clickLoginMenu();
	        
	        // 2. Trigger the failing login workflow using your original login method
	        loginPage.login(invalidUser.email, invalidUser.password);
	        
	        // 3. Extracted the error message directly from the login page instance
	        String actualErrorMsg = loginPage.getLoginErrorMessage();

	        Assert.assertEquals(actualErrorMsg, "Your email or password is incorrect!");
	    }

	    @Test(priority = 4, description = "Test Case 4: Logout User from active storefront view", dependsOnMethods = "verifyValidLoginWorkflow")
	    public void verifySessionLogoutWorkflow(ITestContext context) {
	        UserData.UserProfile user = JsonReader.getValidUser();
	        String threadSafeEmail = (String) context.getAttribute("sharedEmail");

	        LoginPage loginPage = homePage.clickLoginMenu()
	            .login(threadSafeEmail, user.password)
	            .clickLogoutMenu();

	        Assert.assertTrue(loginPage.getSignupErrorMessage() == null, "Error: Unexpected signup validation context found."); 
	    }

	    @Test(priority = 5, description = "Test Case 5: Register User with existing duplicate email address", dependsOnMethods = "verifyUserRegistrationWorkflow")
	    public void verifyDuplicateEmailRegistrationPrevented(ITestContext context) {
	        UserData.UserProfile user = JsonReader.getValidUser();
	        String threadSafeEmail = (String) context.getAttribute("sharedEmail");

	        SignupPage signupPage = homePage.clickLoginMenu()
	            .registerNewUserIntent(user.name, threadSafeEmail);

	        Assert.assertEquals(signupPage.getSignupErrorMessage(), "Email Address already exist!");
	    }
}
