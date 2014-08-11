package cms.timeout;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sairam on 11/08/2014.
 */
public class StepDefinitions {

    //WebDriver instance variable
    static WebDriver driver;
    //Data
    String username = "srikanth", password = "Sairam99";
    public static boolean isTextPresent(String text)
    {
        return getVisibleText().contains(text);
    }

    public static String getVisibleText()
    {
        return driver.findElement(By.tagName("body")).getText();
    }

    public void selectFromDropDownMenu(By by,String text)
    {
        Select sel=new Select(driver.findElement(by));
        sel.selectByVisibleText(text);
    }
    public void navigateToVenuesPage()
    {
        driver.findElement(By.linkText("Venues")).click();
    }

    @Before
    public void setUp()throws NullPointerException{
        driver = new FirefoxDriver();
        driver.get("http://admin.qa04.d/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        try {
//            //Creating a Firefox WebDriver Object
//            // driver = new FirefoxDriver();
//            //Create an object for Desired Capabilities
//            DesiredCapabilities caps = DesiredCapabilities.firefox();
//            caps.setCapability("platform", "Windows 8");
//            caps.setCapability("version", "3.0");
//
//            // Create the connection to Sauce Labs to run the tests
//            driver = new RemoteWebDriver(
//                    new URL("http://msg9985168472:208ff962-cf96-4c23-8717-287d123f612d@ondemand.saucelabs.com:80/wd/hub"),caps);
//                   // new java.net.URL("http://daninsauce:855db6d4-a7fe-4382-8f64-b9f75cb1ee65@ondemand.saucelabs.com:80/wd/hub"),caps);
//            //Open the URL
//            driver.get("http://admin.qa04.d/");
//            //Maximising the window
//            driver.manage().window().maximize();
//            //waiting for page to load
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
   }
    @After
    public void stop() {
        driver.quit();
    }


    public void login(String username, String password) {
        try {
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.name("Login")).click();
        } catch (Exception e) {
            System.out.println("Invalid Credentials");
        }
    }

    @Given("^Editor is on Login Page$")
    public void Editor_is_on_Login_Page() throws Throwable {
    Assert.assertTrue(isTextPresent("Login"));
    }

    @When("^Editor Enters Username and Password and selects Login$")
    public void Editor_Enters_Username_and_Password_and_selects_Login(){

        this.login(username, password);
    }

    @Then("^Editor should be logged successfully$")
    public void Editor_should_be_logged_successfully(){
       Assert.assertTrue(driver.findElement(By.linkText(username)).isDisplayed());
    }

    @When("^Editor logout$")
    public void Editor_logout()throws NullPointerException{
        //Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
        driver.findElement(By.className("login_logout")).isDisplayed();
        driver.findElement(By.className("login_logout")).click();
    }

    @Then("^editor should navigate to LoginPage$")
    public void editor_should_navigate_to_LoginPage(){
        // Express the Regexp above with the code you wish you had
      //  Assert.assertEquals("Login", driver.findElement(By.name("Login")).getText());
        Assert.assertTrue(isTextPresent("Login"));

    }

    @Given("^Editor is logged-In$")
    public void Editor_is_logged_In() throws InterruptedException {
        this.login(username,password);
        Thread.sleep(2000);
//      Assert.assertTrue(driver.findElement(By.className(username)).isDisplayed());
//      driver.findElement(By.className("login_logout")).isDisplayed();
    }

    @Given("^Editor is on HomePage$")
    public void Editor_is_on_HomePage() {
    driver.findElement(By.linkText("Dashboard")).isDisplayed();

    }

    @Then("^Editor can see Venues Option$")
    public void Editor_can_see_Add_Venue_Option() {
        driver.findElement(By.linkText("Venues")).isDisplayed();

    }

    @When("^Editor selects Venues Option$")
    public void Editor_selects_Venues_Option() {
        driver.findElement(By.linkText("Venues")).click();

    }

    @Then("^Editor should navigate to Venues Page$")
    public void Editor_should_navigate_to_Add_Venue_Page() {
        Assert.assertTrue(this.isTextPresent("Venues"));

    }

    @When("^Editor selects Add Venue Option$")
    public void Editor_selects_Add_Venue_Option() {
    driver.findElement(By.xpath(".//*[@id='content']/h1/a")).click();
    }

    @Then("^Editor should be navigate to Add Venue Page$")
    public void Editor_should_be_navigate_to_Add_Venue_Page() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.findElement(By.cssSelector("h1")).getText().contains("New venue"));
    }
    @When("^Editor selects Language as '(.*)'$")
    public void Editor_selects_Language_as_British_English(String language) {
    this.selectFromDropDownMenu(By.id("venueCreate_language"),language);
    }

    @When("^Editor enters Name as '(.*)' and City as '(.*)'$")
    public void Editor_enters_Name_as_Sri_and_City_as_London(String Name,String City) {

   try {
       driver.findElement(By.id("venueCreate_name")).sendKeys(Name + new Random().nextInt());
   }catch (Exception e)
   {
       System.out.println("We have found some similar sounding venues, please review them below before saving this venue ");
   }
    driver.findElement(By.id("venueCreate_city")).sendKeys(City);
    }

    @When("^Editor selects Site as '(.*)'$")
    public void Editor_selects_Site_as_UK_London(String site) {
    this.selectFromDropDownMenu(By.id("venueCreate_site"),site);

    }

    @When("^selects Save Option$")
    public void selects_Save_Option() throws InterruptedException {
    driver.findElement(By.id("form_submit")).click();
    Thread.sleep(2000);
    }

    @Then("^Editor should see message as '(.*)'$")
    public void Editor_should_see_a_Saved_message(String message) {
    Assert.assertTrue(isTextPresent(message));
    }

    @Then("^Editor should be navigate to '(.*)' Page$")
    public void Editor_should_be_navigate_to_Edit_Venue_Page(String editVenuePage) throws InterruptedException {
    Assert.assertTrue(isTextPresent(editVenuePage));
    Thread.sleep(1000);
    driver.findElement(By.linkText("Logout")).click();

    }

    @Given("^Editor is in Venues Page$")
    public void Editor_is_in_Venues_Page() {
     this.login(username,password);
     this.navigateToVenuesPage();
     Assert.assertTrue(isTextPresent("Venues"));

    }
    @When("^Editor selects Name as '(.*)' and Site as '(.*)'$")
    public void Editor_selects_Name_as_Sri_and_City_as_London(String name,String Site) {
    driver.findElement(By.id("venue_filter_name")).sendKeys(name);
    this.selectFromDropDownMenu(By.id("venue_filter_site"),Site);
    }


    @When("^selects filter$")
    public void selects_filter() throws InterruptedException {
    driver.findElement(By.xpath(".//*[@id='filterBox']/form/fieldset/div[4]/button")).click();
    Thread.sleep(1000);

    }

    @Then("^Editor should see the list of venues with the name 'Sri' and Site 'Spain - Madrid'$")
    public void Editor_should_see_the_list_of_venues_with_the_name_Sri_and_Site_SpainMadrid() {
    Assert.assertTrue(this.isTextPresent("Sri"));
    Assert.assertTrue(this.isTextPresent("Spain - Madrid"));
    driver.findElement(By.linkText("Logout")).click();
    }


}
