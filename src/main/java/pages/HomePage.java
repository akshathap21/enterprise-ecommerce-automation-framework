package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    
    private By loginLink = By.xpath("//a[contains(@href,'/login')]");
    private By productsLink = By.xpath("//a[contains(@href,'/products')]");
    private By cartLink = By.xpath("//a[contains(@href,'/view_cart')]");
    private By logoutMenuLink = By.xpath("//a[contains(@href,'/logout')]");
    private By contactUsMenuLink = By.xpath("//a[contains(@href,'/contact_us')]");
    private By productsMenuLink = By.xpath("//a[contains(@href,'/products')]");
    

    // Footer subscription locators for upcoming test cases
    private By subscriptionEmailField = By.id("susbscribe_email");
    private By subscriptionButton = By.id("subscribe");
    private By subscriptionSuccessText = By.xpath("//div[@id='success-subscribe']/div");
    
    // An element that only appears when a user is successfully logged in
    private By loggedInUserHeaderMessage = By.xpath("//i[@class='fa fa-user']/parent::a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginMenu() {
        actions.doClick(loginLink);
        return new LoginPage(driver); // Chains directly to LoginPage
    }

 

    public CartPage clickCartMenu() {
        actions.doClick(cartLink);
        return new CartPage(driver); // Chains directly to CartPage
    }
    
    public LoginPage clickLogoutMenu() {
        actions.doClick(logoutMenuLink);
        return new LoginPage(driver);
    }

    /**
     * Fluent transition routing to the Contact Us surface layout.
     * @return Fresh ContactUsPage object instance.
     */
    public ContactUsPage clickContactUsMenu() {
        actions.doClick(contactUsMenuLink);
        return new ContactUsPage(driver); // Chains to ContactUsPage
    }

    /**
     * Fluent transition routing to the Product Inventory surface layout.
     * @return Fresh ProductsPage object instance.
     */
    public ProductPage clickProductsMenu() {
        actions.doClick(productsMenuLink);
        return new ProductPage(driver); // Chains to ProductsPage
    }

    /**
     * Reusable Footer Operation: Scrolls to the bottom and triggers a subscription.
     */
    public HomePage subscribeToNewsletter(String email) {
        actions.scrollToElement(subscriptionEmailField);
        actions.doSendKeys(subscriptionEmailField, email);
        actions.doClick(subscriptionButton);
        return this;
    }

    public String getSubscriptionSuccessAlertText() {
        return actions.doGetText(subscriptionSuccessText);
    }
    
    public boolean isUserLoggedInMessageDisplayed() {
        return actions.doIsDisplayed(loggedInUserHeaderMessage);
    }

    public String getLoggedInUserText() {
        return actions.doGetText(loggedInUserHeaderMessage);
    }
    
 
}
