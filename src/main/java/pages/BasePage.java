package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BrowserActions;

public class BasePage {

	protected  WebDriver driver;
	protected BrowserActions actions;// <--- THIS IS COMPOSITION
	/**
	 * We are not using extends ElementActions. Instead, 
	 * we simply declared ElementActions actions as a variable inside the class. 
	 * The BasePage now has an ElementActions tool inside it. This is Composition.
	 * */
	
	  // Constructor mapping driver and synchronization utilities
    public BasePage(WebDriver driver) {
    	this.driver = driver;
    	this.actions = new BrowserActions(driver);
    	}
}
