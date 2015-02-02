package cms.timeout;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by freelance on 15/08/2014.
 */
public class DashBoardPage extends BaseClass{
//   WebDriver driver = BrowserFactory.getDriver();
    Utils utils=new Utils();
    public void Logout()
    {
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isEnabled());
        driver.findElement(By.linkText("Logout")).click();

    }

    public void navigateToVenuesPage()
    {
        driver.findElement(By.linkText("Venues")).click();
    }

  public void navigateToEventsPage()
    {
driver.findElement(By.linkText("Events")).click();
    }

    public void navigateToFilmsPage(){driver.findElement(By.linkText("Films")).click();}


    public void addTaxonomy() {
        //click on the categories
        driver.findElement(By.xpath("//li[@id='node_306']/div")).click();
        //select category
          driver.findElement(By.xpath("//li[@id='node_1725']/span")).click();
        //double click on the category to add that to taxonomy
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath("//li[@id='node_1725']/span")));
        action.build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //select the primary category by drag n drop to primary tag area
        WebElement element = driver.findElement(By.xpath("(//li[@id='node_1725']/span)[2]"));

        WebElement target = driver.findElement(By.cssSelector("span.ui-droppable"));

        action.dragAndDrop(element, target).perform();

        driver.findElement(By.xpath("//div[@id='tagger_instance1']/button")).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        try {
//             utils.isTextPresent("Tagging updated.");
//        }catch (Exception e)
//        {
//            System.out.println("Taxonomy is not saving"+e.getMessage());
//        }
    }

//    public void addFilmTaxonomy() {
//        //click on the categories
//        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/div")).click();
//        //select category
//        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/ul/li[17]/div")).click();
//        // driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/ul/li[17]/ul/li[6]/span")).click();
//        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        //double click on the category to add that to taxonomy
//        Actions action = new Actions(driver);
//        action.doubleClick(driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[17]/ul/li[6]/span")));
//        action.build().perform();
//
////        action.moveToElement(driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[17]/ul/li[6]/span"))).doubleClick().build().perform();
////        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//        //select the primary category by drag n drop to primary tag area
//        WebElement element = driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[17]/ul/li[6]/span"));
//        WebElement target = driver.findElement(By.xpath("//div[@id='primaryTag']/span"));
////        (new Actions(driver)).dragAndDrop(element, target).perform();
//         action.dragAndDrop(element, target).perform();
//
//        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/button")).click();
//        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
//        try {
//            Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/span")).getText().contains("Tagging updated."));
//        }catch (Exception e)
//        {
//            System.out.println("Taxonomy is not saving" + e.getMessage());
//        }
//
//    }
}

