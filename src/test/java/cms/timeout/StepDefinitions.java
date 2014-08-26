package cms.timeout;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.table.DataTable;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by freelance on 15/08/2014.
 */
public class StepDefinitions extends BaseClass{

    // Test Data -----------------------------------------------
    String username = "autotest-admin", password = "outtime99";
    String random= String.valueOf(new Random().nextInt());


    //-------------------------------------------------------------------------------------------
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
    public void selectWithIndex(By by,int index)
    {
        Select sel=new Select(driver.findElement(by));
        sel.selectByIndex(index);
    }
    public void navigateToVenuesPage()
    {
        driver.findElement(By.linkText("Venues")).click();
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
//BrowserFactory----------------------------------
// WeDriver or Remote WebDriver Initialisations
    @Before
    public void setUp()throws NullPointerException{
//        driver = new FirefoxDriver();
//        driver.get("http://admin.qa04.d/");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

        try {
            //Creating a Firefox WebDriver Object
//           driver = new FirefoxDriver();
            //Create an object for Desired Capabilities
            DesiredCapabilities caps = DesiredCapabilities.firefox();
            caps.setCapability("platform", "Windows 8");
            caps.setCapability("version", "3.0");

            // Create the connection to Sauce Labs to run the tests
            driver = new RemoteWebDriver(
           // new URL("http://timeoutdigital:b6315b1b-3640-4a38-aa72-54c4fa2ca570@ondemand.saucelabs.com:80/wd/hub"),caps);
                    new URL("http://cb_sgoud843:d0a05dc9-8302-4210-9ed0-9a74b760afe2@ondemand.saucelabs.com:80/wd/hub"),
                    caps);
            //Open the URL
            driver.get("http://admin.qa04.d/");
            //Maximising the window
            driver.manage().window().maximize();
            //waiting for page to load
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @After
    public void stop() {
     driver.quit();
    }

    //------------LOGIN------------------
    @Given("^I am Logged-In$")
    public void I_am_Logged_In() {
    this.login(username,password);
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    Assert.assertTrue(driver.findElement(By.linkText(username)).isDisplayed());
    }
    //------------------ADD Venue-----------------------------
    @When("^I add a Venue$")
    public void I_add_a_Venue() throws InterruptedException {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        DashBoardPage dashBoardPage=new DashBoardPage();
        dashBoardPage.navigateToVenuesPage();
        Thread.sleep(2000);
        try {
            driver.findElement(By.linkText("+ Add venue")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
    }

    @When("^I supply the information$")
    public void I_supply_the_information(DataTable arg1) throws InterruptedException {
        // Express the Regexp above with the code you wish you had
        // For automatic conversion, change DataTable to List<YourType>

       List<String> raw = Arrays.asList("British English", "Srikanth", "London", "UK - London");

       // DataTable dataTable = DataTable.create(raw, Locale.getDefault(),"Language", "Name", "City", "Site");
        this.selectFromDropDownMenu(By.id("venueCreate_language"),raw.get(0));
        try {
            String a= raw.get(1)+random;
            driver.findElement(By.id("venueCreate_name")).sendKeys(a);
        }catch (Exception e)
        {
            System.out.println("We have found some similar sounding venues, please review them below before saving this venue ");
        }
        driver.findElement(By.id("venueCreate_city")).sendKeys(raw.get(2));
        this.selectFromDropDownMenu(By.id("venueCreate_site"),raw.get(3));
    }

    @When("^I save it$")
    public void I_save_it() {

        driver.findElement(By.id("form_submit")).click();

    }

    @Then("^the Venue is created and should see message as '(.*)'$")
    public void the_Venue_is_created_and_should_see_message_as_The_venue_was_created_successfully_(String message) {
       driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Assert.assertTrue(this.isTextPresent(message));
    }

    @Then("^it should be navigate to the '(.*)' Page$")
    public void it_should_be_navigate_to_the_Edit_venue_Page(String editVenuePage) {
   Assert.assertTrue(this.isTextPresent(editVenuePage));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.findElement(By.linkText("Logout")).click();
    }

//--------------ADD Taxonomy---------------------

    @When("^I add taxonomy for the venue$")
    public void taxonomy()
    {
        //driver.findElement(By.linkText("Taxonomy")).click();
        driver.findElement(By.xpath(".//*[@id='column2']/ul/li[2]/a")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id='tagger_1']/div[1]/ul/li[4]/div")).click();
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@id='tagger_1']/div[1]/ul/li[4]/ul/li[4]/span"))).doubleClick().build().perform();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);WebElement element = driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[4]/span"));

        WebElement target = driver.findElement(By.xpath("//div[@id='primaryTag']/span"));

        (new Actions(driver)).dragAndDrop(element, target).perform();
//        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
    }

@When("^I go back to Edit Venue Page$")
    public void backToVenue()
    {
      driver.findElement(By.linkText("Edit Venue")).click();
    }
//--------------LOGOUT-------------------------------------
    @When("^I logout$")
public void I_logout()
{
    driver.findElement(By.linkText("Logout")).click();
}
@Then("^I should redirect to Login Page$")
public void backtologinpage()
{
    Assert.assertEquals("Login", driver.findElement(By.xpath(".//*[@id='content']/h1")).getText());
}
// -----------------Edit Venue--------------------------------------
@Given("^I am on the Venues Page$")
public void I_am_on_the_Venues_Page() {
    this.navigateToVenuesPage();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    Assert.assertTrue(this.isTextPresent("Venues"));

}
    @When("^I search for the venue with the Name as '(.*)' and Site as '(.*)'$")
    public void I_search_for_the_venue_with_the_Name_as_Srikanth_and_Site_as_UK_London(String name,String site) {
        driver.findElement(By.id("venue_filter_name")).sendKeys(name);
        this.selectFromDropDownMenu(By.id("venue_filter_site"), site);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }
    @When("^I selects UpdatedInLast as '(.*)'$")
    public void I_selects_UpdatedInLast(String UpdatedInLast)
    {
        this.selectFromDropDownMenu(By.id("venue_filter_updated_last"),UpdatedInLast);
//        this.selectWithIndex(By.id("venue_filter_updated_last"),index);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='filterBox']/form/fieldset/div[4]/button")).click();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }
    @When("^I select the recently created Venue with the name '(.*)'$")
    public void I_select_the_recently_created_Venue_with_the_name(String name) {
    driver.findElement(By.partialLinkText(name)).click();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @Then("^I should navigate to the Edit Venue Page$")
    public void I_should_navigate_to_the_Edit_Venue_Page() {
    }

    @When("^I changes the BuildingNo as '(.*)' and Author as '(.*)' and Status as '(.*)'$")
    public void I_changes_the_BuildingNo_as_Sri_Building_and_Author_as_Srikanth_and_Status_as_Completed(String BuildingNo,String Author,String Status) throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.findElement(By.id("venueEdit_building_no")).clear();
        driver.findElement(By.id("venueEdit_building_no")).sendKeys(BuildingNo);
        driver.findElement(By.id("venueEdit_author")).clear();
        driver.findElement(By.id("venueEdit_author")).sendKeys(Author);
    this.selectFromDropDownMenu(By.id("venueEdit_status"), Status);
        Thread.sleep(3000);
    }

    @When("^I save the Venue$")
    public void I_save_the_Venue() {
    driver.findElement(By.id("form_submit")).click();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @Then("^I should see the message as '(.*)'$")
    public void I_should_see_the_message_as_The_venue_was_saved_successfully_(String message) {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Assert.assertTrue(this.isTextPresent(message));


    }


}
