package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage  extends BasePage{ // <--- THIS IS INHERITANCE
	

	
	// Explicitly isolated 'By' locators anchored to form containers
	private By login_singup_button = By.xpath("//div[contains(@class, 'shop-menu')]//a[@href='/login']");
	private By loginEmailField = By.xpath("//div[@class='login-form']//input[@data-qa='login-email']");
    private By loginPasswordField = By.xpath("//div[@class='login-form']//input[@data-qa='login-password']");
    private By loginButton = By.xpath("//button[@data-qa='login-button']");
    private By loginErrorAlert = By.xpath("//form[@action='/login']/p");
    
    private By signupNameField = By.xpath("//div[@class='signup-form']//input[@data-qa='signup-name']");
    private By signupEmailField = By.xpath("//div[@class='signup-form']//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']");
    private By signupErrorAlert = By.xpath("//form[@action='/signup']/p");
    
    
    // Passing the driver instance up to the BasePage parent constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }   
    

    /**
     * Fluent action pattern method.
     * Performs login steps and returns itself to allow ongoing verification tracking.
     */
    public HomePage  login(String email, String password) {
    	actions.doClick(login_singup_button);

        actions.doSendKeys(loginEmailField, email);
        actions.doSendKeys(loginPasswordField, password);
        actions.doClick(loginButton);
        return new HomePage(driver); // Returns the page instance cleanly
    }
    
    
    /**
     * Fluent handler routing user creation paths cleanly over to the Registration Form profile.
     * @return A targeted instance of the intermediate SignupPage layout.
     */
    public SignupPage registerNewUserIntent(String name, String email) {
        actions.doSendKeys(signupNameField, name);
        actions.doSendKeys(signupEmailField, email);
        actions.doClick(signupButton);
        return new SignupPage(driver); // Navigates onto the details registration form page view
    }

    public String getLoginErrorMessage() {
        return actions.doGetText(loginErrorAlert);
    }

    public String getSignupErrorMessage() {
        return actions.doGetText(signupErrorAlert);
    }




}



/**
 * Interview Questions
 * Why didn't you just make your page classes extend your utility class?"
 * Extending a utility class directly creates tight coupling and violates the single responsibility principle. 
 * A page class is not a utility. Instead, I designed a BasePage inheritance layer combined with Composition. 
 * This keeps my architecture decoupled, preserves true object relationships, and makes the framework scalable
 *  for parallel thread execution."
 *  
 * "I used Composition by instantiating my ElementActions utility inside a common BasePage. 
 * Then, my specific page classes like LoginPage use Inheritance to extend BasePage. 
 * This gives my page classes direct, clean access to all the wrapper interaction methods without breaking 
 * object-oriented design principles or tightly coupling my pages to a utility class."
 * */
 