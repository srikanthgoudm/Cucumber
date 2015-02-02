package cms.timeout;

import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by freelance on 20/11/2014.
 */
public class Pages extends BaseClass{
    Utils utils=new Utils();
    String random= String.valueOf(new Random().nextInt());
    public void searchPage(String keyword,String pageSite,String status)
    {
        driver.findElement(By.id("filter_q")).sendKeys(keyword);
        utils.selectFromDropDown(By.id("filter_site"), pageSite);
        utils.selectFromDropDown(By.id("filter_status"),status);
        driver.findElement(By.xpath("//button[@value='Filter']")).click();

    }

    public void addPage(String pageName,String title,String subTitle,String site,String language,String pageType)
    {
        driver.findElement(By.linkText("+ Add page")).click();
        Assert.assertTrue(utils.isTextPresent("New page"));

        if (utils.isElementPresent(By.id("pageCreate_alias")))
        {
         driver.findElement(By.id("pageCreate_alias")).sendKeys(pageName+random);
        }
        driver.findElement(By.id("pageCreate_title")) .sendKeys(title+random);
        driver.findElement(By.id("pageCreate_description")).sendKeys(subTitle);
        utils.selectFromDropDown(By.id("pageCreate_site"),site);
        utils.selectFromDropDown(By.id("pageCreate_locale"),language);
        utils.selectFromDropDown(By.id("pageCreate_type"),pageType);
    }
}
