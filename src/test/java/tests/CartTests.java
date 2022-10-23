package tests;

import org.json.simple.JSONArray;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ViewCartPage;
import utils.logs.Log;

import java.lang.reflect.Method;
import java.util.List;
import static fileReaderManager.ReadFromFiles.getJsonArrValueByKey;
import static utils.extentreports.ExtentTestManager.startTest;

public class CartTests extends BaseTests{
    public static String testDataJsonFile = "cartTestData.json" ;
    ViewCartPage viewCartPage;
    JSONArray productTextTextData;
    JSONArray quantityTextData;
    JSONArray quantityIterationsTextData;
    JSONArray totalPriceIterationsTextData;

    @BeforeClass
    public void loadTestData() {
        Log.info("Load test data for cart tests");
        productTextTextData =   getJsonArrValueByKey(testDataJsonFile, "prodTextDataKey");
        quantityTextData =  getJsonArrValueByKey(testDataJsonFile, "quantTextDataKey");
        quantityIterationsTextData = getJsonArrValueByKey(testDataJsonFile,"quantIterationsDataKey");
        totalPriceIterationsTextData = getJsonArrValueByKey(testDataJsonFile,"totalPriceIterationsDataKey");

    }

    //Test case TC_13: Check if user can see the  quantity based on the times the product was added in product page.
    // If he added multiple product multiple times
    @Test(priority = 0, description = "Check quantity number in cart reflects number of products added")
    public void checkQuantityForMultipleProductsAddedMultipleTimesTest(Method method)
    {
        //ExtentReports Description
        startTest(method.getName(), "Check quantity number in cart reflects number of products added");

        Log.info("Add \"Blue Top\" product 2 times to cart");
        // Add first product 2 times to cart
        productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        productsPage.addFirstProductToCart();
        productsPage.clickContinueShopping();
        //Add second product to cart
        Log.info("Add \"Men Tshirt\" product 1 time to cart");
        productsPage.addSecondProductToCart();
        viewCartPage= productsPage.clickViewCart();

        //check products Description and Quantity in cart is
        Log.info("Verify that first product description is \"Blue Top\" and the quantity =2");
        Log.info("Verify that second product description is \"Men Tshirt\"  and the quantity =1 ");
        List<String> productsDescription=viewCartPage.getTextOfProductsInCart() ;
        List<String> productsQuantity=viewCartPage.getTextOfQuantitiesInCart() ;

        for (int i = 0; i < productsDescription.size(); i++) {
            Assert.assertEquals(productsDescription.get(i), productTextTextData.get(i), "Description for product "+i+ " should be "+ productTextTextData.get(i) );
            for (int j=0; j<productsQuantity.size(); j++)
                Assert.assertEquals(productsQuantity.get(j),quantityTextData.get(j), "Quantity for product "+j+ " should = "+quantityTextData.get(j));
        }
    }

    //Test case TC_14: The total price should change according to the quantity of the added products
    @Test(priority = 1, description = "check Total Price is Change when Quantity of products changed")
    public void checkTotalPriceChangeAccordingToQuantityTest(Method method)
    {

        //ExtentReports Description
        startTest(method.getName(), "Check Total Price is Change when Quantity of products changed");

        Log.info("Add \"Men Shirt\" to cart");
        //Add Product "Men Shirt" to cart and check the Quantity=1 and Total Price= Rs. 400 On View Cart Page
        productsPage = new ProductsPage(driver);
        productsPage.addSecondProductToCart();
        Log.info("click view cart");
        viewCartPage = productsPage.clickViewCart();
        Log.info("check the Quantity=1 and Total Price= Rs. 400");
        Assert.assertEquals(viewCartPage.getQuantity(),quantityIterationsTextData.get(0),"Quantity = " + quantityIterationsTextData.get(0));
        Assert.assertEquals(viewCartPage.getTotalPrice(), totalPriceIterationsTextData.get(0) ,"Total Price = "+totalPriceIterationsTextData.get(0));

        //Add Product "Men Shirt" to cart and check the Quantity=2 and Total Price= Rs. 800 On View Cart Page
        Log.info("go to Products page");
        productsPage = viewCartPage.clickProductsButton();
        Log.info("Add \"Men Shirt\" to cart");
        productsPage.addSecondProductToCart();
        Log.info("click view cart");
        viewCartPage = productsPage.clickViewCart();
        Log.info("check the Quantity=2 and Total Price= Rs. 800");
        Assert.assertEquals(viewCartPage.getQuantity(),quantityIterationsTextData.get(1),"Quantity = "+quantityIterationsTextData.get(1));
        Assert.assertEquals(viewCartPage.getTotalPrice(), totalPriceIterationsTextData.get(1) ,"Total Price = "+ totalPriceIterationsTextData.get(1));
    }


}
