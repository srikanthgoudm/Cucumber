package cms.timeout;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by freelance on 19/11/2014.
 */
public class EventPage extends BaseClass{
    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String random= String.valueOf(new Random().nextInt());
    public void searchEvent(String name,String site,String status,String UpdatedInLast)
    {
        driver.findElement(By.id("event_filter_name")).clear();
        driver.findElement(By.id("event_filter_name")).sendKeys(name);
        utils.selectFromDropDown(By.id("event_filter_site"), site);
        utils.selectFromDropDown(By.id("event_filter_status"),status);
        utils.selectFromDropDown(By.id("event_filter_updated_last"),UpdatedInLast);
        driver.findElement(By.xpath("//button[@value='Filter']")).click();

    }
    public void addEvent(String site,String language,String event)
    {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();

        dashBoardPage.navigateToEventsPage();
        try {
            driver.findElement(By.linkText("+ Add event")).click();
        } catch (Exception e) {
            System.out.println("Element Not Found");
        }
        utils.selectFromDropDown(By.id("eventCreate_site"),site);
        utils.selectFromDropDown(By.id("eventCreate_language"),language);
        driver.findElement(By.id("eventCreate_name")).clear();
        driver.findElement(By.id("eventCreate_name")).sendKeys(event+random);
        }
}
