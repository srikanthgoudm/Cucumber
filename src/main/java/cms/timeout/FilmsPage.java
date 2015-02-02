package cms.timeout;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by freelance on 20/11/2014.
 */
public class FilmsPage extends BaseClass{
    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String random= String.valueOf(new Random().nextInt());


    public void searchFilm(String film)
    {
        driver.findElement(By.id("name")).sendKeys(film);
        driver.findElement(By.xpath("//button[@value='Search']")).click();
    }

    public void addFilm(String language,String originialTitle,String title,String author,String rating)
    {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        DashBoardPage dashBoardPage=new DashBoardPage();
        dashBoardPage.navigateToFilmsPage();
        try {
            driver.findElement(By.linkText("+ Add film")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
        utils.selectFromDropDown(By.id("filmCreate_language"),language);
        driver.findElement(By.id("filmCreate_original_title")).sendKeys(originialTitle+random);
        driver.findElement(By.id("filmCreate_title")).sendKeys(title+random);
        driver.findElement(By.id("filmCreate_author")).sendKeys(author);
        utils.selectFromDropDown(By.id("filmCreate_editor_rating"),rating);
    }
}
