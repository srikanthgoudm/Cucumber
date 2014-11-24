package cms.timeout;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by freelance on 20/11/2014.
 */
public class PostsPage extends BaseClass{
    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    String random= String.valueOf(new Random().nextInt());

   public void searchPost(String blogName,String recentPost)
   {
       utils.selectFromDropDown(By.id("postList_blogs"),blogName);
       driver.findElement(By.partialLinkText(recentPost)).click();
   }

    public void addPost(String postName,String category,String site,String bodyText)
    {
        driver.findElement(By.linkText("+ Add post")).click();
        List<String> raw = Arrays.asList("Test Post Title", "Movies", "Chicago Blog (Chicago - En)", "Test Body Text");

        driver.findElement(By.id("postEdit_title")).sendKeys(postName+random);
        utils.selectFromDropDown(By.id("postEdit_taxonomy"),category);
        utils.selectFromDropDown(By.id("postEdit_blogId"),site);
//        String bodyText= raw.get(3)+random;
        ((JavascriptExecutor)driver).executeScript("tinyMCE.activeEditor.setContent('<h1>This is Body Text Header</h1> Test Body Text')");

    }

}
