package cms.timeout;
import com.opera.core.systems.OperaDriver;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by freelance on 17/09/2014.
 */
public abstract class BrowserFactory extends BaseClass {
    //public static WebDriver driver;

    public static WebDriver StartBrowser(String Browser, String URL) throws MalformedURLException, InterruptedException {
        if (driver == null || !isSessionActive()) {
            driver = startRemoteWebBrowser(Browser, URL);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static boolean isSessionActive() {
        try {

            return driver.findElements(By.tagName("body")).size() > 0;

        } catch (Exception e) {

        }
        return false;
    }


    public static void QuitBrowser() {
//		getDriver().quit();
        //  WebDriver d = getDriver();
        driver = null;
    }

    protected static WebDriver startRemoteWebBrowser(String browser, String URL) {

        if (false) {
            try {

                System.out.println("grid started...");
                // Create an object for Desired Capabilities
//                System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
                DesiredCapabilities caps=DesiredCapabilities.firefox();
//                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                caps.setCapability("version", "3.0");
                caps.setCapability("platform", "Windows 8");
//                caps.setCapability("browserName",System.getProperty("browser"));
                caps.setCapability("browserName","firefox");

                Selenium selenium = new DefaultSelenium("ci02.d", 4444, "*firefox", "http://jenkins-ci.org/");
                // Create the connection to Sauce Labs to run the tests
//                driver = new RemoteWebDriver(new URL("http://timeoutdigital:b6315b1b-3640-4a38-aa72-54c4fa2ca570@ondemand.saucelabs.com:80/wd/hub"), caps);
//                Selenium grid URL when accessed from Jenkins (ci02)
              driver = new RemoteWebDriver(new URL("http://selenium02:4444/wd/hub"), caps);
//                Jenkins (ci02) selenium grid URL when accessed from outside Jenkins (comment-out to use local grid)
//                driver = new RemoteWebDriver(new URL("http://10.10.61.113:4444/wd/hub"), caps);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            try {
                if (browser.equalsIgnoreCase("Firefox")) {
                    FirefoxProfile firefoxprofile = new FirefoxProfile();
                    firefoxprofile.setAssumeUntrustedCertificateIssuer(true);
                    firefoxprofile.setAcceptUntrustedCertificates(true);
                    driver = new FirefoxDriver(firefoxprofile);
                } else if (browser.equalsIgnoreCase("chrome")) {

                    System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver.exe");
                    driver = new ChromeDriver();

                } else if (browser.equalsIgnoreCase("IE")) {
                    System.setProperty("webdriver.ie.driver", "C:\\Automation\\IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                } else if (browser.equalsIgnoreCase("Safari")) {
                    driver = new SafariDriver();
                } else if (browser.equalsIgnoreCase("Opera")) {
                    driver = new OperaDriver();
                } else {
                    throw new RuntimeException("Browser give " + browser + " did not load..");
                }
            }
                catch(Exception e)
                {
                    throw new RuntimeException("Browser give " + browser + " did not load..");
                }
            }
            driver.get(URL);
            return driver;
        }
    }

