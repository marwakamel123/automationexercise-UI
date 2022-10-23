package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ProductsPage extends BasePage {
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    private By productSreachTextField = By.id("search_product");
    private By submitSearchButton = By.id("submit_search");
    private By searchProductTextResults = By.cssSelector(".productinfo >p");

    private By allProductsDispalyedText = By.cssSelector(".features_items>h2");
    private By firstProduct = By.cssSelector("[class=\"productinfo text-center\"]>[data-product-id=\"1\"]");
    private By secondProduct = By.cssSelector("[class=\"productinfo text-center\"]>[data-product-id=\"2\"]");
    private By clickContinueShoppingButton = By.className("btn-success");
    private By clickViewCartButton =By.cssSelector("[class=\"text-center\"]>a");


    public void addFirstProductToCart() {
        scrollToElement(productSreachTextField);
        hover( this.driver, locateElement(firstProduct),firstProduct);
        clickElement(By.cssSelector("[class=\"overlay-content\"] [data-product-id=\"1\"]"));
        //forceClickOnElement(firstProduct);
    }
    public void addSecondProductToCart() {
        scrollToElement(productSreachTextField);
        hover( this.driver, locateElement(secondProduct),secondProduct);
        clickElement(By.cssSelector("[class=\"overlay-content\"] [data-product-id=\"2\"]"));
        //forceClickOnElement(secondProduct);
    }
    public void clickContinueShopping()
    {
        clickElement(clickContinueShoppingButton);
    }
    public ViewCartPage clickViewCart()
    {
        clickElement(clickViewCartButton);
        return new ViewCartPage(driver);
    }

    public void searchProduct(String searchText) {
        scrollToElement(productSreachTextField);
        typeOnInputField(productSreachTextField, searchText);
        clickElement(submitSearchButton);
        //return new ProductSearchResultPage(driver);
    }
    public List<String> getTextOfDisplayedProducts()
    {
        scrollToElement(productSreachTextField);
        return getTextOfElementsList(searchProductTextResults);
    }

}
