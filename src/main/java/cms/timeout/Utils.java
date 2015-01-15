package cms.timeout;

import cms.timeout.BaseClass;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.NoSuchElementException;

/**
 * Created by Sairam on 11/08/2014.
 */
public class Utils extends BaseClass{

  // WebDriver driver = BrowserFactory.getDriver();

    private boolean acceptNextAlert = true;
    public static void selectFromDropDown(By by,String text)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByVisibleText(text);

    }
    public static void selectFromDropDown1(By by, int index)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByIndex(index);
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static boolean isElementPresent(By element)
    {
        try
        {
            return driver.findElement(element).isDisplayed();

        }
        catch (NoSuchElementException e){
            return false;
        }
    }


    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public static boolean isTextPresent(String text)
    {
        return getVisibleText().contains(text);
    }

    public static String getVisibleText()
    {
        driver = BrowserFactory.getDriver();
        return driver.findElement(By.tagName("body")).getText();
    }

    public static void selectCheckBox(By by,boolean b) {
        driver = BrowserFactory.getDriver();
        if(b)
        {
            if(!driver.findElement(by).isSelected())
            {
                driver.findElement(by).click();
            }
        }
        else
        {
            if(driver.findElement(by).isSelected())
            {
                driver.findElement(by).click();
            }
        }
        wait(1000);
    }

    public static void wait(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void waitForElement(By locator) {
        (new WebDriverWait(driver, 10)).
                until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    private void waitForPageToLoad() {

        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };

        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.assertFalse("Timeout waiting for Page Load Request to complete.", true);
        }
    }
}
