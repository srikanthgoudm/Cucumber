package cms.timeout;
import cucumber.annotation.After;
import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.table.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
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
    //String username = "sri-editor", password = "srikanth";
    String username = "autotest-admin", password = "outtime99";

//    String URL="http://admin.qa04.d/";
    String URL="http://admin.staging01.ldn3.timesout.net/";

//    Properties prop=new Properties();
//    String URL=System.getProperty("URL1");

    String random= String.valueOf(new Random().nextInt());

    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    LoginPage loginPage=new LoginPage();

    @Before
    public void StartBrowser()throws MalformedURLException,InterruptedException {
        try {
            BrowserFactory.StartBrowser("Firefox", URL);
            driver = BrowserFactory.driver;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @After
    public void stop() {
  driver.quit();
    }
    //------------LOGIN------------------
    @Given("^I am Logged-In$")
    public void loggedIn() {
    loginPage.login(username, password);
    Assert.assertTrue(driver.findElement(By.linkText(username)).isDisplayed());
    }
    //------------------ADD Venue-----------------------------
    @When("^I add a Venue$")
    public void addVenue() throws InterruptedException {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        dashBoardPage.navigateToVenuesPage();
        try {
            driver.findElement(By.linkText("+ Add venue")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
    }
    @When("^I supply the information$")
    public void supplyVenueInformation(DataTable arg1) throws InterruptedException {
        List<String> raw = Arrays.asList("UK - London","British English", "Srikanth", "London") ;
        utils.selectFromDropDown(By.id("venueCreate_site"),raw.get(0));
        utils.selectFromDropDown(By.id("venueCreate_language"),raw.get(1));
        try {
            String venueName= raw.get(2)+random;
            driver.findElement(By.id("venueCreate_name")).sendKeys(venueName);
        }catch (Exception e)
        {
            System.out.println("We have found some similar sounding venues, please review them below before saving this venue ");
        }
        driver.findElement(By.id("venueCreate_city")).sendKeys(raw.get(3));

    }

    @When("^I save it$")
    public void save() {
        driver.findElement(By.id("form_submit")).click();
        if (driver.findElement(By.cssSelector("BODY")).getText().contains("We have found some similar sounding events, please review them below before saving this event")){
            driver.findElement(By.id("form_submit")).click();}
    }

    @Then("^the Venue is created and should see message as '(.*)'$")
    public void venueSavedMessage(String message) {
       driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));
    }

        @Then("^the Event is created and should see message as '(.*)'$")
        public void eventSavedMessage(String message) {

            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            Assert.assertTrue(utils.isTextPresent(message));
    }
    @Then("^the Page is created and should see message as '(.*)'$")
    public void pageSavedMessage(String message) {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));
    }


//--------------ADD Taxonomy---------------------

    @When("^I add taxonomy for Event")
    public void addTaxonomy1() throws InterruptedException {
         //click on taxonomy link
        driver.findElement(By.xpath(".//*[@id='column2']/ul/li[3]/a")).click();
       dashBoardPage.addTaxonomy();
    }
    @When("^I add taxonomy for Venue")
    public void addTaxonomy2() throws InterruptedException {
        //click on taxonomy link
        driver.findElement(By.xpath("/html/body/div[3]/div[3]/div[3]/ul/li[2]/a")).click();
        dashBoardPage.addTaxonomy();
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
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    Assert.assertTrue(utils.isTextPresent("Venues"));
}
    @When("^I search for the venue with the Name as '(.*)' and Site as '(.*)'$")
    public void searchVenue(String name,String site) {
        driver.findElement(By.id("venue_filter_name")).sendKeys(name);
        utils.selectFromDropDown(By.id("venue_filter_site"), site);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @When("^I change the Event status as '(.*)'$")
    public void changeEventStatus(String Status)
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        utils.selectFromDropDown(By.id("eventEdit_status"),Status);
    }
    @When("^I change the Venue status as '(.*)'$")
    public void changeVenueStatus(String Status)
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        driver.findElement(By.xpath(".//*[@id='filterBox']/form/fieldset/div[4]/button")).click();

    }
    @When("^I select the recently created Venue with the name '(.*)'$")
    public void selectRecentlyAddedVenue(String name) {
    driver.findElement(By.partialLinkText(name)).click();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Then("^I should be navigate to the '(.*)' Page$")
    public void navigateToEditEventOrVenuePage(String editPage) {
        Assert.assertTrue(utils.isTextPresent(editPage));

    }

    @When("^I changes the BuildingNo as '(.*)' and Author as '(.*)' and Status as '(.*)'$")
    public void changeVenueDetails(String BuildingNo,String Author,String Status) throws InterruptedException {
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.id("venueEdit_building_no")).clear();
        driver.findElement(By.id("venueEdit_building_no")).sendKeys(BuildingNo);
        driver.findElement(By.id("venueEdit_author")).clear();
        driver.findElement(By.id("venueEdit_author")).sendKeys(Author);
        utils.selectFromDropDown(By.id("venueEdit_status"), Status);
    }

    @When("^I save the Venue$")
    public void saveVenue() {
    driver.findElement(By.id("form_submit")).click();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }

    @Then("^I should see the message as '(.*)'$")
    public void EVFSavedMesssage(String message) {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

            Assert.assertTrue(utils.isTextPresent(message));


    }

    @When("^I add a Event$")
    public void addEvent() throws InterruptedException {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        DashBoardPage dashBoardPage=new DashBoardPage();
        dashBoardPage.navigateToEventsPage();
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


    //Film

    @When("^I add a Film$")
    public void addFilm() {
        driver.findElement(By.linkText("Dashboard")).isDisplayed();
        DashBoardPage dashBoardPage=new DashBoardPage();
        dashBoardPage.navigateToFilmsPage();
        try {
            driver.findElement(By.linkText("+ Add film")).click();
        } catch (Exception e) {
            System.out.println("Element Not Fount");
        }
    }

    @When("^I supply the Film information$")
    public void supplyFilmInfo(DataTable arg1) {
        List<String> raw = Arrays.asList("British English", "Test Film", "Test Title", "Author", "4");
        utils.selectFromDropDown(By.id("filmCreate_language"),raw.get(0));
        // DataTable dataTable = DataTable.create(raw, Locale.getDefault(),"Language", "Name", "City", "Site");
        String OriginialTitle= raw.get(1)+random;
        driver.findElement(By.id("filmCreate_original_title")).sendKeys(OriginialTitle);
        String title= raw.get(2)+random;
        driver.findElement(By.id("filmCreate_title")).sendKeys(title);
        driver.findElement(By.id("filmCreate_author")).sendKeys(raw.get(3));
        utils.selectFromDropDown(By.id("filmCreate_editor_rating"),raw.get(4));
   }
    @Then("^the Film is created and should see message as '(.*)'$")
    public void filmSavedMessage(String message) {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));

    }

    @When("^I add taxonomy for Film$")
    public void I_add_taxonomy_for_Film() {
      //select Taxonomy link
      driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/ul/li[5]/a")).click();
      //Add Primary Tag
      dashBoardPage.addTaxonomy();

    }

    @When("^I go back to Edit Film Page$")
    public void gotoEditFilmPage() {driver.findElement(By.linkText("Edit Film")).click();
    }

    @When("^I change the Film status as '(.*)'$")
    public void changeFilmStatus(String status) {
    utils.selectFromDropDown(By.id("filmEdit_status"),status);

    }

    @Given("^I am on the Films Page$")
    public void onFilmsPage() {
    driver.findElement(By.linkText("Films")).click();
    utils.isTextPresent("Add Film");

    }

    @When("^I search for the Film '(.*)'$")
    public void searchFilm(String film) {
    driver.findElement(By.id("name")).sendKeys(film);
    driver.findElement(By.xpath("//*[@id='form']/div[3]/button")).click();
    }

    @When("^I select the recently created Film '(.*)'$")
    public void selectRecentlyAddedFilm(String recentFilm) {
   driver.findElement(By.partialLinkText(recentFilm)).click();

    }

    @When("^I save the Film$")
    public void saveFilm() {
    driver.findElement(By.id("form_submit")).click();

    }


    @When("^I changes the Short Desc as '(.*) and Editor rating as '(.*)' and Author as '(.*)' and Status as '(.*)'$")
    public void changeDetails(String shortdesc,String rating,String author,String status) {
        driver.findElement(By.id("filmEdit_description")).clear();
        driver.findElement(By.id("filmEdit_description")).sendKeys(shortdesc);
        utils.selectFromDropDown(By.id("filmEdit_editor_rating"),rating);
        driver.findElement(By.id("filmEdit_author")).clear();
        driver.findElement(By.id("filmEdit_author")).sendKeys(author);
        utils.selectFromDropDown(By.id("filmEdit_status"), status);

   }
//------------------Pages-------------------------------
@Given("^I am on the Pages Page$")
public void onThePages() {
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
   // driver.getCurrentUrl().
driver.get(URL+"pages");
Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"content\"]/h1/a")).isDisplayed());

}

    @When("^I add a Page$")
    public void addPage() {
driver.findElement(By.xpath("/html/body/div/div[3]/h1/a")).click();
        Assert.assertTrue(utils.isTextPresent("New page"));

    }
    @When("^I supply the Page information$")
    public void supplyPageInfo(DataTable arg1) {

        List<String> raw = Arrays.asList("TestPageName","Test Title","Test SubTitle","UK - London","British English", "Feature");
        if(utils.isElementPresent(By.id("pageCreate_alias")))
        {
        String pageName=raw.get(0)+random;
        driver.findElement(By.id("pageCreate_alias")).sendKeys(pageName);
        }
        String pageTitle= raw.get(1)+random;
        driver.findElement(By.id("pageCreate_title")) .sendKeys(pageTitle);
        driver.findElement(By.id("pageCreate_description")).sendKeys(raw.get(2));
        utils.selectFromDropDown(By.id("pageCreate_site"),raw.get(3));
        utils.selectFromDropDown(By.id("pageCreate_locale"),raw.get(4));
        utils.selectFromDropDown(By.id("pageCreate_type"),raw.get(5));
        }


    @When("^I add taxonomy for Page$")
    public void pageTaxonomy() {

        driver.findElement(By.linkText("Page set up")).click();
        Assert.assertTrue(utils.isTextPresent("Set in taxonomy"));
        driver.findElement(By.linkText("Set in taxonomy")).click();
        dashBoardPage.addTaxonomy();
    }

    @When("^I go back to Edit page$")
    public void backToEditPage() {

        driver.findElement(By.linkText("Edit Page")).isDisplayed();
        driver.findElement(By.linkText("Edit Page")).click();
    }

    @When("^I change the Page status as '(.*)'$")
    public void changePageStatus(String status) {
    driver.findElement(By.id("pageEdit_status")).sendKeys(status);

    }

    @When("^I search for the Page with Keyword '(.*)' and Site as '(.*)'$")
    public void searchForPageSite(String keyword ,String pageSite) {
    driver.findElement(By.id("filter_q")).sendKeys(keyword);
    utils.selectFromDropDown(By.id("filter_site"), pageSite);
    driver.findElement(By.xpath("//*[@id=\"filterBox\"]/form/fieldset/div[5]/button")).click();

    }

    @When("^I select the recently created Page with the name '(.*)'$")
    public void searchRecentlyAddedPage(String pageName) {
    driver.findElement(By.partialLinkText(pageName)).click();

    }

    @When("^I changes event Subtitle as '(.*)' and status as '(.*)'$")
    public void changeDetails1(String subtitle,String status) {
        driver.findElement(By.id("pageEdit_description")).clear();
        driver.findElement(By.id("pageEdit_description")).sendKeys(subtitle);
        utils.selectFromDropDown(By.id("pageEdit_status"), status);
    }

    @When("^I save the Page$")
    public void savePage() {driver.findElement(By.id("form_submit")).click();
    }
//--------------------------Blog----------------------------------

    @Given("^I am on the Blogs Page$")
    public void I_am_on_the_Blogs_Page() {
        driver.get(URL+"blogs");
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/h1/a")).isDisplayed());

    }

    @When("^I add a Blog$")
    public void I_add_a_Blog() {
driver.findElement(By.xpath("/html/body/div[3]/div/h1/a")).click();
    }

    @When("^I supply the Blog information$")
    public void supplyBlogInformation(DataTable arg1) {
   List<String> raw = Arrays.asList("Test BlogName","/london/blogs/","UK - London","British English");
        String blogName=raw.get(0)+random;
        driver.findElement(By.id("blogCreate_name")).sendKeys(blogName);
        driver.findElement(By.id("blogCreate_slug")).sendKeys(raw.get(1));
        utils.selectFromDropDown(By.id("blogCreate_site"),raw.get(2));
        utils.selectFromDropDown(By.id("blogCreate_language"),raw.get(3));

    }

    @When("^I save blog$")
    public void saveBlog() {
        driver.findElement(By.xpath("/html/body/div[3]/div/form/div/div/button")).isDisplayed();
        driver.findElement(By.xpath("/html/body/div[3]/div/form/div/div/button")).click();
    }

    @Then("^the Blog is created and should see message as '(.*)'$")
    public void blogSavedMessage(String message) {
    Assert.assertTrue(utils.isTextPresent(message));

    }

    @Then("^I should see recently added blog '(.*)' in the blog list$")
    public void verifyRecentlyAddedBlog(String blogName) {
        Assert.assertTrue(utils.isTextPresent(blogName));
    }

 //------------------Post--------------------
 @Given("^I am on the Posts Page$")
 public void onThePostsPage() {
     driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
     // driver.getCurrentUrl()
     try {
         driver.findElement(By.linkText("Posts")).isDisplayed();
         driver.findElement(By.linkText("Posts")).click();
     }catch (Exception e)
     {
         System.out.println("Posts link not found");
         driver.get(URL+"posts");
     }

     Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/h1/a")).isDisplayed());
 }

    @Then("^I selects blog name '(.*)'$")
    public void selectBlogName(String blogname) {
    utils.selectFromDropDown(By.id("postList_blogs"),blogname);
    }

    @When("^I add a Post$")
    public void addPost() {
    driver.findElement(By.linkText("+ Add post")).click();

    }

    @When("^I supply postInformation$")
    public void supplyPostInfo(DataTable arg1) {

        List<String> raw = Arrays.asList("Test Post Title","Movies","Chicago Blog (Chicago - En)","Test Body Text");
        String postTitle=raw.get(0)+random;
        driver.findElement(By.id("postEdit_title")).sendKeys(postTitle);
        utils.selectFromDropDown(By.id("postEdit_taxonomy"),raw.get(1));
        utils.selectFromDropDown(By.id("postEdit_blogId"),raw.get(2));
//        String bodyText= raw.get(3)+random;
        ((JavascriptExecutor)driver).executeScript("tinyMCE.activeEditor.setContent('<h1>This is Body Text Header</h1> Test Body Text')");

    }
    @When("^I save the Post and Publish$")
    public void savePost() {
        driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[2]/div[1]/button")).click();
        if(utils.isAlertPresent()){
            driver.switchTo().alert();
            driver.switchTo().alert().accept();
            driver.switchTo().defaultContent();
        }
       driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[3]/button[2]")).click();
    }

    @Then("^the Post is created and should see message as '(.*)'$")
    public void postSavedMessage(String message) {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));

    }

    @When("^I go to '(.*)' Page$")
    public void goToPostListPage(String page) {
    driver.findElement(By.linkText(page)).click();
    }

    @Then("^I Should see recently added Post '(.*)'$")
    public void verifyRecentlyAddedPost(String post) {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(post));

    }

    @When("^I selects recently added post '(.*)'$")
    public void selectRecentlyAddedPost(String recentPost) {
    driver.findElement(By.partialLinkText(recentPost)).click();
    }
    @When("^I selects recently modified post '(.*)'$")
    public void selectRecentlyModifiedPost(String recentPost) {
        driver.findElement(By.partialLinkText(recentPost)).click();
    }

    @When("^I changes post title as '(.*)' and Body Text as '(.*)'$")
    public void changePostInfo(String posttitle,String bodytext) {
        driver.findElement(By.id("postEdit_title")).clear();
        driver.findElement(By.id("postEdit_title")).sendKeys(posttitle);
        ((JavascriptExecutor)driver).executeScript("tinyMCE.activeEditor.setContent('Modified Test Body Text')");
    }

    @When("^I Update the Post$")
    public void updatePost() {
        try {
            driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[3]/button[2]")).isDisplayed();
            driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[3]/button[2]")).click();
        }catch (Exception e){
            System.out.println("Can't find the Update button");
        }
    }

    @When("^I Delete the Post$")
    public void deletePost() {
        try {
            driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[3]/button[1]")).isDisplayed();
            driver.findElement(By.xpath("/html/body/div[3]/div/form/div[2]/div[1]/div[3]/button[1]")).click();

        } catch (Exception e) {
            System.out.println("Can't find the Delete button");
        }
    }
        @Then("^I confirm the Delete$")
        public void confirmDelete() {
            driver.findElement(By.xpath("/html/body/div[4]/div/div/div[2]")).isDisplayed();
            driver.findElement(By.xpath("/html/body/div[4]/div/div/div[3]/a[1]")).click();
        }

    }

