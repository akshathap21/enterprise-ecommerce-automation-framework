package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {

	 // Account Information Form Matrix
    private By mrRadioBtn = By.id("id_gender1");
    private By passwordField = By.id("password");
    private By daysDropdown = By.id("days");
    private By monthsDropdown = By.id("months");
    private By yearsDropdown = By.id("years");
    
 // Checkbox Vectors
    private By newsletterCheckbox = By.id("newsletter");
    private By specialOffersCheckbox = By.id("optin");

    // Address Structure Fields
    private By firstNameField = By.id("first_name");
    private By lastNameField = By.id("last_name");
    private By companyField = By.id("company");
    private By address1Field = By.id("address1");
    private By countryDropdown = By.id("country");
    private By stateField = By.id("state");
    private By cityField = By.id("city");
    private By zipcodeField = By.id("zipcode");
    private By mobileNumberField = By.id("mobile_number");
    private By createAccountButton = By.xpath("//button[@data-qa='create-account']");

    // Account Created Success Frame Indicators
    private By accountCreatedHeaderMessage = By.xpath("//h2[@data-qa='account-created']/b");
    private By continueWorkflowButton = By.xpath("//a[@data-qa='continue-button']");

    
	public SignupPage(WebDriver driver) {
		super(driver);
	}

	
	public SignupPage fillAccountInformation(String password, String day, String month, String year) {
        actions.doClick(mrRadioBtn);
        actions.doSendKeys(passwordField, password);
        
        // Harnessing native drop-down processing sequences via explicit actions integration
        new Select(driver.findElement(daysDropdown)).selectByValue(day);
        new Select(driver.findElement(monthsDropdown)).selectByVisibleText(month);
        new Select(driver.findElement(yearsDropdown)).selectByValue(year);
        
        actions.doClick(newsletterCheckbox);
        actions.doClick(specialOffersCheckbox);
        return this;
    }
	
	
	/**
     * Fills address fields and submits the signup form.
     * @return Continues the chain tracking back onto the confirmation context views.
     */
    public SignupPage fillAddressDetails(String fName, String lName, String comp, String addr, 
                                         String country, String state, String city, String zip, String mobile) {
        actions.doSendKeys(firstNameField, fName);
        actions.doSendKeys(lastNameField, lName);
        actions.doSendKeys(companyField, comp);
        actions.doSendKeys(address1Field, addr);
        
        new Select(driver.findElement(countryDropdown)).selectByVisibleText(country);
        
        actions.doSendKeys(stateField, state);
        actions.doSendKeys(cityField, city);
        actions.doSendKeys(zipcodeField, zip);
        actions.doSendKeys(mobileNumberField, mobile);
        
        actions.doClick(createAccountButton);
        return this;
    }

    /**
     * Verifies that user registration completed successfully.
     */
    public String getAccountCreatedSuccessMessage() {
        return actions.doGetText(accountCreatedHeaderMessage);
    }

    /**
     * Transitions from the profile confirmation display back into the primary storefront view.
     */
    public HomePage clickContinueButton() {
        actions.doClick(continueWorkflowButton);
        return new HomePage(driver);
    }


	public String getSignupErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
