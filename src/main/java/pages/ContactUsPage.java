package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage{

	 private By contactHeader = By.xpath("//h2[contains(text(),'Get In Touch')]");
	    private By nameField = By.xpath("//input[@data-qa='name']");
	    private By emailField = By.xpath("//input[@data-qa='email']");
	    private By subjectField = By.xpath("//input[@data-qa='subject']");
	    private By messageField = By.xpath("//textarea[@data-qa='message']");
	    private By uploadFileInput = By.xpath("//input[@name='upload_file']");
	    private By submitButton = By.xpath("//input[@data-qa='submit-button']");
	    private By successBanner = By.xpath("//div[contains(@class,'alert-success')]");

	    public ContactUsPage(WebDriver driver) {
	        super(driver);
	    }
	    
	    public boolean isContactHeaderDisplayed() {
	        return actions.doIsDisplayed(contactHeader);
	    }

	    public ContactUsPage fillContactForm(String name, String email, String subject, String message, String filePath) {
	        actions.doSendKeys(nameField, name);
	        actions.doSendKeys(emailField, email);
	        actions.doSendKeys(subjectField, subject);
	        actions.doSendKeys(messageField, message);
	        actions.uploadFile(uploadFileInput, filePath);
	        return this;
	    }
	    
	    public ContactUsPage submitForm() {
	        actions.doClick(submitButton);
	        actions.handleAlertAccept(); // Automatically confirms the native browser popup
	        return this;
	    }

	    public String getSuccessMessageText() {
	        return actions.doGetText(successBanner);
	    }
}
