package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    private By allProductsHeader = By.xpath("//h2[@class='title text-center' and text()='All Products']");
    private By searchInputField = By.id("search_product");
    private By searchSubmitButton = By.id("submit_search");
    private By searchedProductsHeader = By.xpath("//h2[contains(text(),'Searched Products')]");
    private By firstProductResultText = By.xpath("//div[@class='productinfo text-center']/p");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isAllProductsHeaderVisible() {
        return actions.doIsDisplayed(allProductsHeader);
    }

    public ProductPage searchProduct(String productName) {
        actions.doSendKeys(searchInputField, productName);
        actions.doClick(searchSubmitButton);
        return this;
    }

    public boolean isSearchedProductsHeaderVisible() {
        return actions.doIsDisplayed(searchedProductsHeader);
    }

    public String getFirstProductResultName() {
        return actions.doGetText(firstProductResultText);
    }
}
