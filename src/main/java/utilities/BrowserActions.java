package utilities;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	  // Constructor mapping driver and synchronization utilities
    public BrowserActions(WebDriver driver) {
    	this.driver = driver;
    	this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	}
    
    
    
    /**
     * Centralized method to wait for visibility and click an element safely.
     * Prevents ElementNotInteractableException.
     */
    public void doClick(By locator) {
    	
    	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
    

    /**
     * Centralized method to safely clear and send text to an input field.
     * Prevents ElementNotVisibleException.
     */
    public void doSendKeys(By locator, String value) {
    	WebElement element = 	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	element.clear();
    	element.sendKeys(value);
    }
    
    
    /**
     * Centralized method to fetch text cleanly from a web element.
     */
    public String doGetText(By locator) {
    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	return element.getText();
    }
    
    
    /**
     * Centralized method to check if an element is currently displayed on the DOM layout.
     */
    public boolean doIsDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    
    /**
     * Handles JavaScript Alert Popups cleanly by waiting for presence and accepting.
     */
    public void handleAlertAccept() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
    
    
    /**
     * Senior Interaction Hook: Scrolls directly down to an element using JavaScriptExecutor.
     * Prevents ElementClickInterceptedException on slow web structures.
     */
    public void scrollToElement(By locator) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
    }
    
    
    /**
     * Direct file path string transmission bypassing manual windows OS dialogs.
     */
    public void uploadFile(By locator, String absoluteFilePath) {
        // File input fields must not use explicit click loops before transmission
        driver.findElement(locator).sendKeys(absoluteFilePath);
    }
}
