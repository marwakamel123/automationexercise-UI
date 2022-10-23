package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.ProductsPage;
import utils.logs.Log;

import java.io.File;
import java.io.IOException;

import static fileReaderManager.ReadFromFiles.getPropertyByKey;

public class BaseTests {
    public WebDriver driver;
    ProductsPage productsPage;

    public static String configPropertyFileName =  "configData.properties" ;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void navigateToProductsPagePreCondition()
    {
        Log.info("Method Test Begins,Opening Products Page");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get((String) getPropertyByKey(configPropertyFileName , "APP_URL"));

    }

    @AfterMethod
    public void a_takeScreenshotForFailedTests (ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {FileUtils.copyFile(screenshot, new File("./images/" +result.getName() +".png"));}
            catch (IOException e) {System.out.println("I/O Error");}
        }
    }
    @AfterMethod
    public void z_quitDriver () {
        Log.info("Method test ends, Quitting!");
        driver.quit();}



}
