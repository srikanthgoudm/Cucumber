package cms.timeout;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by freelance on 17/09/2014.
 */
public abstract class BrowserFactory extends BaseClass{
    //public static WebDriver driver;

    public static WebDriver StartBrowser(String Browser,String URL1) throws MalformedURLException, InterruptedException
    {
        if(driver==null||!isSessionActive())
        {
            driver = startRemoteWebBrowser(Browser,URL1);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

    public static boolean isSessionActive()
    {
        try {

            return driver.findElements(By.tagName("body")).size()>0;

        }
        catch(Exception e)
        {

        }
        return false;
    }


    public static void QuitBrowser()
    {
//		getDriver().quit();
        //  WebDriver d = getDriver();
        driver=null;
    }
    protected static WebDriver startRemoteWebBrowser(String browser,String URL1) {

        if (true) {
            try {

                System.out.println("grid started...");
                // Create an object for Desired Capabilities
                DesiredCapabilities caps = DesiredCapabilities.firefox();
                caps.setCapability("version", "3.0");
                caps.setCapability("platform", "Windows 8");

                // Create the connection to Sauce Labs to run the tests
                driver = new RemoteWebDriver(new URL("http://timeoutdigital:b6315b1b-3640-4a38-aa72-54c4fa2ca570@ondemand.saucelabs.com:80/wd/hub"), caps);
                //Open the URL

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        else
        {
            try
            {
                if(browser.equalsIgnoreCase("Firefox"))
                {
                    FirefoxProfile firefoxprofile = new FirefoxProfile();
                    firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
                    firefoxprofile.setAcceptUntrustedCertificates(true);
                    driver = new FirefoxDriver(firefoxprofile);

                }
                else if(browser.equalsIgnoreCase("chrome"))
                {
                    //System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
                    //  driver=new ChromeDriver();
                    System.setProperty("webdriver.chrome.driver", "\\Users\\freelance\\Automation\\Chromeexe/\\chromedriver");
                    driver=new ChromeDriver();
                }
                else
                    throw new RuntimeException("Browser give "+browser+ " did not load..");
            }
            catch(Exception e)
            {
                throw new RuntimeException("Browser give "+browser+ " did not load..");
            }
        }
        driver.get(URL1);
        return driver;
    }
}
