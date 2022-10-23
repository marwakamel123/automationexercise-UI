package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class ViewCartPage extends BasePage{
    public ViewCartPage(WebDriver driver) {
        super(driver);
    }
    private By productTextList = By.cssSelector("[class=\"cart_description\"] a");
    //private By quantityText = By.cssSelector("[class=\"cart_quantity\"] button");
    private By quantityText = By.cssSelector(".cart_quantity>button");
    //private By productsButton =By.cssSelector("[class=\"nav navbar-nav\"] :nth-of-type(2)>a");
    private By productsButton =By.cssSelector(".navbar-nav :nth-of-type(2)>a");
    private By totalPriceText = By.className("cart_total_price");


    public String getQuantity()
    {
       return getTextOfElement(quantityText);
    }
    public String getTotalPrice()
    {
        return getTextOfElement(totalPriceText);
    }
    public List<String> getTextOfProductsInCart() {return getTextOfElementsList(productTextList);}
    public List<String> getTextOfQuantitiesInCart() {return getTextOfElementsList(quantityText);}
    public ProductsPage clickProductsButton()
    {
        clickElement(productsButton);
        return new ProductsPage(driver);
    }

}
