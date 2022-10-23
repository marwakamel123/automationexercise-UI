package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.logs.Log;

import java.lang.reflect.Method;
import java.util.List;

import static fileReaderManager.ReadFromFiles.getJsonArrValueByKey;
import static fileReaderManager.ReadFromFiles.getJsonObjByKey;
import static utils.extentreports.ExtentTestManager.startTest;

public class ProductsSearchTests extends BaseTests{

    public static String testDataJsonFile = "searchProductTestData.json" ;
    private String searchKeywordsTC8 ;
    private String searchKeyword1TC8 ;
    private String searchKeyword2TC8 ;
    private String searchKeywordTC2 ;
    private Long numOfProducts;

    @BeforeClass
    public void loadTestData() {
        Log.info("Load test data for product search tests");
        searchKeywordsTC8 = (String) getJsonObjByKey(testDataJsonFile, "searchTC8Key");
        searchKeyword1TC8 = (String) getJsonObjByKey(testDataJsonFile, "searchKeyword1TC8Key");
        searchKeyword2TC8 = (String) getJsonObjByKey(testDataJsonFile,"searchKeyword2TC8Key");
        searchKeywordTC2  = (String) getJsonObjByKey(testDataJsonFile,"searchKeywordTC2Key");
        numOfProducts     = (Long) getJsonObjByKey(testDataJsonFile,"numOfprodKey");
    }

    //Test Case TC_2: check if user types one appropriate key word in productSearchTextBox then click searchButton,
    // all related search results will contain the search keyword. The key word does not match any  part of item category
    @Test(priority = 0, description = "Search with keyword that is part of category")
    public void searchWithOneKeywordTest(Method method){
        //ExtentReports Description
        startTest(method.getName(), "Search with keyword that is part of category");

        Log.info("Search with keyword"+searchKeywordTC2);
        productsPage= new ProductsPage(driver);
        productsPage.searchProduct(searchKeywordTC2);
        List<String> displayedProductsList =  productsPage.getTextOfDisplayedProducts();
        //check if 3 products are displayed
        Log.info("Verify that 3 products are displayed");
        Assert.assertEquals(displayedProductsList.size(),numOfProducts,numOfProducts +" products should be displayed");
        //check if all results contains keyword "sleeveless"
        Log.info("Verify that all results contains keyword \"sleeveless\"");
        for (int i=0;i<displayedProductsList.size();i++)
        {   Assert.assertTrue(displayedProductsList.get(i).contains(searchKeywordTC2),"product should contains keyword " + searchKeywordTC2);}


    }


    //Test Case TC_8 :check if user types appropriate keywords in productSearchTextBox then click searchButton,
    // The best match search results will appear first then related correct search results
    @Test(priority = 1, description = "Check that best match search results will appear first then related correct search results")
    public void ExactSearchAppearsFirstTest(Method method)
    {
        //ExtentReports Description
        startTest(method.getName(), "Check that best match search results will appear first then related correct search results");

        Log.info("Search with keyword"+searchKeywordsTC8);
        productsPage= new ProductsPage(driver);
        productsPage.searchProduct(searchKeywordsTC8);
        List<String> displayedProductsList =  productsPage.getTextOfDisplayedProducts();
        //check if exact match is first displayed followed by relative results
        Log.info("Verify that exact match is first displayed");
        Assert.assertEquals(displayedProductsList.get(0), searchKeywordsTC8, "First Item should be " +searchKeywordsTC8);
        //Check the following relevant items are displayed
        Log.info("Verify all relevant products are displayed");
        Assert.assertTrue((displayedProductsList.get(0).contains("Top"))|| (displayedProductsList.get(0).contains("Blue")));
        if (displayedProductsList.size()>1){
            for (int i=1;i<displayedProductsList.size();i++)
            {   Assert.assertTrue((displayedProductsList.get(0).contains("Top"))|| (displayedProductsList.get(0).contains("Blue")),"product should contains blue or top keywords");}
        }
       else{Assert.assertTrue(displayedProductsList.size()!=1,"No relevant products are displayed expected 13 relevant products ");}
    }

}
