package cms.timeout;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by freelance on 15/08/2014.
 */
public class DashBoardPage extends BaseClass{
//   WebDriver driver = BrowserFactory.getDriver();
    public void Logout()
    {
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isEnabled());
        driver.findElement(By.linkText("Logout")).click();
        //driver.findElement(By.xpath("html/body/div[1]/div/div/a")).click();
    }

    public void navigateToVenuesPage()
    {
        driver.findElement(By.linkText("Venues")).click();
    }

  public void navigateToEventsPage()
    {
driver.findElement(By.linkText("Events")).click();
    }

    public void navigateToFilms(){driver.findElement(By.linkText("Events")).click();}
}

