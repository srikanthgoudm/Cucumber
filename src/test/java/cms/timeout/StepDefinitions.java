package cms.timeout;

import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.table.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by freelance on 15/08/2014.
 */
public class StepDefinitions {
//     static WebDriver driver;
    WebDriver driver = BrowserFactory.getDriver();
    // Test Data -----------------------------------------------
    String username = "autotest-admin", password = "outtime99";
    String random= String.valueOf(new Random().nextInt());

    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    LoginPage loginPage=new LoginPage();
    public void login(String username, String password) {
        try {
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.name("Login")).click();
        } catch (Exception e) {
            System.out.println("Invalid Credentials");
        }
    }
    @Before
    public void setUptartBrowser()throws MalformedURLException,InterruptedException{
        try {
            BrowserFactory.StartBrowser("firefox", "http://admin.qa04.d/");
            driver=BrowserFactory.driver;
        }catch(Exception e) {
            e.printStackTrace();
        }
////           if (Utils.isElementPresent(By.linkText("Logout")))
//             if (driver.findElement(By.linkText("Logout")).isDisplayed()){
//               Assert.assertTrue(driver.findElement(By.linkText("Logout")).isEnabled());
//               driver.findElement(By.linkText("Logout")).click();
//           }


        }
//        driver = new FirefoxDriver();
//        driver.get("http://admin.qa04.d/");
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

//        try {
//            //Creating a Firefox WebDriver Object
////           driver = new FirefoxDriver();
//            //Create an object for Desired Capabilities
//            DesiredCapabilities caps = DesiredCapabilities.firefox();
//            caps.setCapability("version", "3.0");
//            caps.setCapability("platform", "Windows 8");
//
//
//            // Create the connection to Sauce Labs to run the tests
//            driver = new RemoteWebDriver(
//           new URL("http://timeoutdigital:b6315b1b-3640-4a38-aa72-54c4fa2ca570@ondemand.saucelabs.com:80/wd/hub"),caps);
////                    new URL("http://cb_sgoud843:d0a05dc9-8302-4210-9ed0-9a74b760afe2@ondemand.saucelabs.com:80/wd/hub"),
////                    caps);
//            //Open the URL
//            driver.get("http://admin.qa04.d/");
//            //Maximising the window
//            driver.manage().window().maximize();
//            //waiting for page to load
//            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
////
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

    @After
    public void stop() {

    driver.quit();
    }
    //------------LOGIN------------------
    @Given("^I am Logged-In$")
    public void I_am_Logged_In() {
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    this.login(username, password);
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    Assert.assertTrue(driver.findElement(By.linkText(username)).isDisplayed());
    }
    //------------------ADD Venue-----------------------------
    @When("^I add a Venue$")
    public void addVenue() throws InterruptedException {
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
    public void supplyVenueInformation(DataTable arg1) throws InterruptedException {
        // Express the Regexp above with the code you wish you had
        // For automatic conversion, change DataTable to List<YourType>

       List<String> raw = Arrays.asList("British English", "Srikanth", "London", "UK - London");

       // DataTable dataTable = DataTable.create(raw, Locale.getDefault(),"Language", "Name", "City", "Site");
        utils.selectFromDropDown(By.id("venueCreate_language"),raw.get(0));
        try {
            String a= raw.get(1)+random;
            driver.findElement(By.id("venueCreate_name")).sendKeys(a);
        }catch (Exception e)
        {
            System.out.println("We have found some similar sounding venues, please review them below before saving this venue ");
        }
        driver.findElement(By.id("venueCreate_city")).sendKeys(raw.get(2));
        utils.selectFromDropDown(By.id("venueCreate_site"),raw.get(3));
    }

    @When("^I save it$")
    public void save() {
        driver.findElement(By.id("form_submit")).click();
        if (driver.findElement(By.cssSelector("BODY")).getText().contains("We have found some similar sounding events, please review them below before saving this event")){
            driver.findElement(By.id("form_submit")).click();}
    }

    @Then("^the Venue is created and should see message as '(.*)'$")
    public void venueSuccessfulMessage(String message) {
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));
    }
    @Then("^the Event is created and should see message as '(.*)'$")
    public void eventSuccessfulMessage(String message) {

           driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
           Assert.assertTrue(utils.isTextPresent(message));

           //System.out.print("We have found some similar sounding events, please review them below before saving this event");
    }

//    @Then("^it should be navigate to the '(.*)' Page$")
//    public void navigateToEditVenuePage(String editVenuePage) {
//   Assert.assertTrue(utils.isTextPresent(editVenuePage));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
////        driver.findElement(By.linkText("Logout")).click();
//    }

//--------------ADD Taxonomy---------------------

    @When("^I add taxonomy")
    public void addTaxonomy1() throws InterruptedException {
         //click on taxonomy link
        driver.findElement(By.xpath(".//*[@id='column2']/ul/li[3]/a")).click();
        //click on the categories
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/div")).click();
        //select category
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/ul/li[4]/span")).click();

        //double click on the category to add that to taxonomy
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@id='tagger_1']/div[1]/ul/li[4]/ul/li[4]/span"))).doubleClick().build().perform();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        //select the primary category by drag n drop to primary tag area
        WebElement element = driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[4]/span"));
        WebElement target = driver.findElement(By.xpath("//div[@id='primaryTag']/span"));
        (new Actions(driver)).dragAndDrop(element, target).perform();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/button")).click();
    }
    @When("^I add taxonomy for Venue")
    public void addTaxonomy2() throws InterruptedException {
        //click on taxonomy link
       // driver.findElement(By.xpath(".//*[@id='column2']/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/ul/li[2]/a")).click();
        //click on the categories
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/div")).click();
        //select category
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/div[2]/div[1]/ul/li[4]/ul/li[4]/span")).click();

        //double click on the category to add that to taxonomy
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//div[@id='tagger_1']/div[1]/ul/li[4]/ul/li[4]/span"))).doubleClick().build().perform();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        //select the primary category by drag n drop to primary tag area
        WebElement element = driver.findElement(By.xpath("//div[@id='tagger_1']/div[2]/ul/li[4]/ul/li[4]/span"));
        WebElement target = driver.findElement(By.xpath("//div[@id='primaryTag']/span"));
        (new Actions(driver)).dragAndDrop(element, target).perform();
        driver.manage().timeouts().implicitlyWait(50,TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[3]/button")).click();
    }
@When("^I go back to Edit Venue Page$")
    public void backToEditVenue()
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
public void backToLoginPage()
{
    Assert.assertEquals("Login", driver.findElement(By.xpath(".//*[@id='content']/h1")).getText());
}
// -----------------Edit Venue--------------------------------------
@Given("^I am on the Venues Page$")
public void onVenuesPage() {
    dashBoardPage.navigateToVenuesPage();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    Assert.assertTrue(utils.isTextPresent("Venues"));
}
    @When("^I search for the venue with the Name as '(.*)' and Site as '(.*)'$")
    public void searchVenue(String name,String site) {
        driver.findElement(By.id("venue_filter_name")).sendKeys(name);
        utils.selectFromDropDown(By.id("venue_filter_site"), site);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @When("^I change the Event status as '(.*)'$")
    public void changeEventStatus(String Status)
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        utils.selectFromDropDown(By.id("eventEdit_status"),Status);
    }
    @When("^I change the Venue status as '(.*)'$")
    public void changeVenueStatus(String Status)
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        utils.selectFromDropDown(By.id("venueEdit_status"),Status);
    }
    @When("^I select status as '(.*)'$")
    public void selectStatus(String Status)
    {
        utils.selectFromDropDown(By.id("venue_filter_status"),Status);
    }
    @When("^I selects UpdatedInLast as '(.*)'$")
    public void selectUpdatedInLast(String UpdatedInLast)
    {
        utils.selectFromDropDown(By.id("venue_filter_updated_last"),UpdatedInLast);
//        this.selectWithIndex(By.id("venue_filter_updated_last"),index);

        driver.findElement(By.xpath(".//*[@id='filterBox']/form/fieldset/div[4]/button")).click();

    }
    @When("^I select the recently created Venue with the name '(.*)'$")
    public void selectRecentlyAddedVenue(String name) {
    driver.findElement(By.partialLinkText(name)).click();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    //checkpoints for navigated to edit event or venue page
    @Then("^I should be navigate to the '(.*)' Page$")
    public void navigateToEditEventOrVenuePage(String editPage) {
        Assert.assertTrue(utils.isTextPresent(editPage));

    }
//    @Then("^I should be navigate to the '(.*)' Page$")
//    public void navigateToEditVenuePage(String editVenuePage) {
//        Assert.assertTrue(utils.isTextPresent(editVenuePage));
//    }

    @When("^I changes the BuildingNo as '(.*)' and Author as '(.*)' and Status as '(.*)'$")
    public void changeVenueDetails(String BuildingNo,String Author,String Status) throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.findElement(By.id("venueEdit_building_no")).clear();
        driver.findElement(By.id("venueEdit_building_no")).sendKeys(BuildingNo);
        driver.findElement(By.id("venueEdit_author")).clear();
        driver.findElement(By.id("venueEdit_author")).sendKeys(Author);
        utils.selectFromDropDown(By.id("venueEdit_status"), Status);
    }

    @When("^I save the Venue$")
    public void saveVenue() {
    driver.findElement(By.id("form_submit")).click();
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    @Then("^I should see the message as '(.*)'$")
    public void EVFSavedMesssage(String message) {
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));
    }

    @When("^I add a Event$")
    public void addEvent() throws InterruptedException {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        DashBoardPage dashBoardPage=new DashBoardPage();
        dashBoardPage.navigateToEventsPage();
        Thread.sleep(2000);
        try {
            driver.findElement(By.linkText("+ Add event")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
    }
    @When("^I supply information$")
    public void supplyEventInfo(DataTable arg1) {
        List<String> raw = Arrays.asList("British English", "Test Event", "UK - London");
        utils.selectFromDropDown(By.id("eventCreate_site"),raw.get(2));
        // DataTable dataTable = DataTable.create(raw, Locale.getDefault(),"Language", "Name", "City", "Site");
        utils.selectFromDropDown(By.id("eventCreate_language"),raw.get(0));
        try {
            String a= raw.get(1)+random;
            driver.findElement(By.id("eventCreate_name")).clear();
            driver.findElement(By.id("eventCreate_name")).sendKeys(a);
        }catch (Exception e)
        {
            System.out.println("We have found some similar sounding events, please review them below before saving this event ");
        }

    }
    @When("^I go back to Edit event Page$")
    public void backToEditEventPage() {
    driver.findElement(By.linkText("Edit Event")).click();
    }

    @Given("^I am on the Events Page$")
    public void eventsPage() {
        dashBoardPage.navigateToEventsPage();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent("Events"));
    }

    @When("^I search for the Event with the Name as '(.*)' and Site as '(.*)'$")
    public void searchEvent(String name,String site) {
        driver.findElement(By.id("event_filter_name")).clear();
        driver.findElement(By.id("event_filter_name")).sendKeys(name);
        utils.selectFromDropDown(By.id("event_filter_site"), site);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @When("^I select the status as '(.*)'$")
    public void selectStatus2(String status) {
    utils.selectFromDropDown(By.id("event_filter_status"),status);

    }

    @When("^I select UpdatedInLast as '(.*)'$")
    public void selectUpdatedInLast2(String UpdatedInLast) {
        utils.selectFromDropDown(By.id("event_filter_updated_last"),UpdatedInLast);
        driver.findElement(By.xpath(".//*[@id='filterBox']/form/fieldset/div[4]/button")).click();
    }

    @When("^I select the recently created Event with the name '(.*)'$")
    public void selectRecentlyAddedEvent(String name) {
        driver.findElement(By.partialLinkText(name)).click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }

//    @Then("^I should navigate to the Edit Event Page$")
//    public void navigateToEditEventPage() {
//    Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[1]/div[3]/h1")).getText().contains("Edit event"));
//
//    }

    @When("^I save the Event$")
    public void saveEvent() {
            driver.findElement(By.id("form_submit")).click();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }
    @When("^I changes event url as '(.*)' and ticket url as '(.*)'$")
    public void changesEventDetails1(String eventurl,String tkturl) {
        driver.findElement(By.id("eventEdit_url")).clear();
        driver.findElement(By.id("eventEdit_url")).sendKeys(eventurl);
        driver.findElement(By.id("eventEdit_ticket_url")).clear();
        driver.findElement(By.id("eventEdit_ticket_url")).sendKeys(tkturl);
        //driver.findElement(By.id("eventEdit_telephone").sendKeys("02033445566");
    }

    @When("^select editorial rating as '(.*)', Author-name as '(.*)' and Status as '(.*)'$")
    public void changeEventDetails2(String rating,String author,String Status) {

        utils.selectFromDropDown(By.id("eventEdit_editorial_rating"),rating);
        driver.findElement(By.id("eventEdit_author")).clear();
        driver.findElement(By.id("eventEdit_author")).sendKeys(author);
        utils.selectFromDropDown(By.id("eventEdit_status"),Status);
    }
}
