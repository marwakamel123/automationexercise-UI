package pages;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public WebDriver driver;
     WebDriverWait wait;
     List<WebElement> elementsList;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public WebElement locateElement(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        return driver.findElement(elementLocator);
    }

    public String getTextOfElement(By elementLocator) {
        return locateElement(elementLocator).getText();
    }

    public List<WebElement> locateListOfElements(By elementLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
        return driver.findElements(elementLocator);
    }
    public List<String> getTextOfElementsList(By locator) {
        List<String> textList = new ArrayList<String>();
        elementsList = locateListOfElements(locator);
        for (int i = 0; i < elementsList.size(); i++) {
            textList.add(elementsList.get(i).getText());
        }
        return textList;

    }
    public WebDriver hover(WebDriver driver, WebElement element, By locator )
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        return  this.driver;

    }
    public void clickElement (By elementLocator) {locateElement(elementLocator).click();}

    public void typeOnInputField (By elementLocator, String text)
    {
        locateElement(elementLocator).sendKeys(text);
    }

    public int[] jasonToIntArray (JSONArray jArray, int l)
    {
        int[] intArray = new int[l];
        if (l>0)
        {
            for (int i=0  ;i<l; i++)
            {
                intArray[i] = (int)jArray.get(i);
                System.out.println(intArray[i]);
            }
            return intArray;

        }
        else return null;
    }
    public int[] getIntListFromStrList(By locator, int l)
    {
        int[] intArray = new int[l];
        List<String> locatedList = getTextOfElementsList(locator);

        if(locateListOfElements(locator).size()>0 )
        {
            for (int i=0  ;i<(locateListOfElements(locator).size()); i++)
            {
                    intArray[i] = Integer.parseInt(locatedList.get(i).replaceAll("[^0-9]", ""));
            }
            return intArray;
        }
        else return null;
    }

    public int[] multiply2Lists (int[] l1, int[] l2, int length)
    {
        int[] result =new int[length];
        for (int i=0  ;i<length; i++) result[i]=l1[i]*l2[i];
        return result;
    }
    public void scrollToElement(By locator)
    {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].scrollIntoView(true);",locateElement(locator));

    }
    /*
 public void scrollToElement (By elementLocator)
 {
     Actions actions = new Actions(driver);
     actions.scrollToElement(locateElement(elementLocator)).perform();
 }
*/
    public boolean checkElementIsDisplayed(By elementLocator)
    {
        return locateElement(elementLocator).isDisplayed();
    }

    public void forceClickOnElement (By elmentLocator)
    {
        WebElement elementToClick = locateElement(elmentLocator);
        JavascriptExecutor js = (JavascriptExecutor)driver ;
        js.executeScript("arguments[0].click()" , elementToClick) ;
    }
}



