package tests;

import org.testng.annotations.Test;
import tests.BaseTest;
import pages.LoginPage;
public class LoginTest extends BaseTest {

	@Test(priority = 1)
    public void verifyLoginWithPropertiesFileData() {
        LoginPage loginPage = new LoginPage(driver);
        
        // Dynamically feeding credentials derived straight out of config.properties
        String email = prop.getProperty("username");
        String password = prop.getProperty("password");
//        
        loginPage.login(email, password);
    }
}
