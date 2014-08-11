package cms.timeout;

import cms.timeout.BaseClass;

/**
 * Created by Sairam on 11/08/2014.
 */
public class Utils{

//    public static void selectFromDropDown(By by,String text)
//    {
//        Select sel = new Select(driver.findElement(by));
//        sel.selectByVisibleText(text);
//    }
//
//    public static boolean isElementPresent(By element)
//    {
//        try
//        {
//            return driver.findElement(element).isDisplayed();
//        }
//        catch (Exception e)
//        {
//            return false;
//        }
//    }

//    public static boolean isTextPresent(String text)
//    {
//        return getVisibleText().contains(text);
//    }

//    public static String getVisibleText()
//    {
//       // driver = BrowserFactory.getDriver();
//        return driver.findElement(By.tagName("body")).getText();
//    }
//
//    public static void selectCheckBox(By by,boolean b) {
//       // driver = BrowserFactory.getDriver();
//        if(b)
//        {
//            if(!driver.findElement(by).isSelected())
//            {
//                driver.findElement(by).click();
//            }
//        }
//        else
//        {
//            if(driver.findElement(by).isSelected())
//            {
//                driver.findElement(by).click();
//            }
     //   }
//        sleep(5);
//    }

    public static void sleep(int i) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
