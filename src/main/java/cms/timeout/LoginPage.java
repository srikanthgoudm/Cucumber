package cms.timeout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by freelance on 18/08/2014.
 */
public class LoginPage extends BaseClass{

    public void login(String username, String password) {
        try {
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.name("Login")).click();
        } catch (Exception e) {
            System.out.println("Invalid Credentials");
        }
    }

    public void forgotPassword()
    {

    }
}
