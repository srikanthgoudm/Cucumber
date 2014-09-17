package cms.timeout;

import cms.timeout.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Sairam on 11/08/2014.
 */
public class Utils extends BaseClass{

  // WebDriver driver = BrowserFactory.getDriver();


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



    public static boolean isElementPresent(By element)
    {
        try
        {
            return driver.findElement(element).isDisplayed();

        }
        catch (Exception e)
        {
            return false;
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
        sleep(5);
    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i*2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
