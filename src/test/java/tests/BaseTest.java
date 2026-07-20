package tests;

import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import core.DriverManager;
import pages.HomePage;
import utilities.ConfigReader;
public class BaseTest {

	  // Making components protected so inheriting test classes can inspect context hooks
    protected WebDriver driver;
    protected Properties prop;
    private ConfigReader configReader;
    
 // Every single test class will inherit this master starting hook
    protected HomePage homePage;
    
    @BeforeMethod
    public void setUp() {
        // Initializing the configuration engine properties stream
        configReader = new ConfigReader();
        prop = configReader.initProperties();
        
        // Spinning up isolated execution environments matching configuration definitions
        driver = DriverManager.getDriver();
        
        // Dynamically pulling target resource locator keys out of properties stream layers
        driver.get(prop.getProperty("url"));
        
        
        // Factory entry point initialization
        homePage = new HomePage(driver);
    }
    
    @AfterMethod
    public void tearDown() {
        // Safely shutting down active container runtime environments
        DriverManager.quitDriver();
    }

}
