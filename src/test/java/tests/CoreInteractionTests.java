package tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.ContactUsPage;
import pages.ProductPage;

public class CoreInteractionTests extends BaseTest {

	
	@Test(priority = 1, description = "Test Case 6: Submit Contact Us Form and Accept JS Alert")
    public void verifyContactUsFormSubmission() {
        // Creating a dummy file locally to pass onto our upload engine cleanly
        File textFile = new File("./src/test/resources/upload_sample.txt");
        try { textFile.createNewFile(); } catch (Exception e) {}
        String cleanPath = textFile.getAbsolutePath();

        ContactUsPage contactUsPage = homePage.clickContactUsMenu();
        Assert.assertTrue(contactUsPage.isContactHeaderDisplayed(), "Contact Header mismatch!");

        String actualSuccessText = contactUsPage
            .fillContactForm("QA Expert", "expert_2026@test.com", "Automation Test Run", "Automating file streams.", cleanPath)
            .submitForm()
            .getSuccessMessageText();

        Assert.assertEquals(actualSuccessText, "Success! Your details have been submitted successfully.");
        textFile.delete(); // Housekeeping
    }
	
	 @Test(priority = 2, description = "Test Case 8 & 9: Verify All Products Layout and Product Search Elements")
	    public void verifyProductSearchEngine() {
	        ProductPage productsPage = homePage.clickProductsMenu();
	        Assert.assertTrue(productsPage.isAllProductsHeaderVisible(), "All Products view validation failed.");

	        String outputItemText = productsPage
	            .searchProduct("Blue Top")
	            .getFirstProductResultName();

	        Assert.assertTrue(productsPage.isSearchedProductsHeaderVisible(), "Searched products grid missing.");
	        Assert.assertTrue(outputItemText.contains("Blue Top"), "Product mismatch in output table layer.");
	    }
}
