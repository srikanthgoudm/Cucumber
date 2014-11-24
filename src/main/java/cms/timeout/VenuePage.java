package cms.timeout;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by freelance on 15/08/2014.
 */
public class VenuePage extends BaseClass{
    DashBoardPage dashBoardPage=new DashBoardPage();
    Utils utils=new Utils();
    String random= String.valueOf(new Random().nextInt());
    public void searchVenue(String name,String site,String UpdatedInLast)
    {
        driver.findElement(By.id("venue_filter_name")).sendKeys(name);
        utils.selectFromDropDown(By.id("venue_filter_site"), site);
        utils.selectFromDropDown(By.id("venue_filter_updated_last"),UpdatedInLast);
        driver.findElement(By.xpath("//*[@id='filterBox']/form/fieldset/div[4]/button")).click();
    }

    public void addVenue(String siteName,String language,String venueName,String city)
    {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        dashBoardPage.navigateToVenuesPage();
        try {
            driver.findElement(By.linkText("+ Add venue")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
        utils.selectFromDropDown(By.id("venueCreate_site"),siteName);
        utils.selectFromDropDown(By.id("venueCreate_language"),language);
        driver.findElement(By.id("venueCreate_name")).clear();
        driver.findElement(By.id("venueCreate_name")).sendKeys(venueName+random);
       driver.findElement(By.id("venueCreate_city")).sendKeys(city);
    }
    public void editVenue()
    {

    }
    public void deleteVenue()
    {

    }
}
