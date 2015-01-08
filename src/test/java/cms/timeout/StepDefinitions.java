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

//   String URL="http://admin.qa06.d/";
    String URL="http://admin.staging01.ldn3.timesout.net/";

//    Properties prop=new Properties();
//    String URL=System.getProperty("URL1");

    String random= String.valueOf(new Random().nextInt());

    Utils utils=new Utils();
    DashBoardPage dashBoardPage=new DashBoardPage();
    LoginPage loginPage=new LoginPage();
    VenuePage venuePage=new VenuePage();
    EventPage eventPage=new EventPage();
    Pages pages=new Pages();
    PostsPage posts=new PostsPage();
    FilmsPage filmsPage=new FilmsPage();

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

    @When("^I add a Venue, I supply the information$")
    public void supplyVenueInformation(DataTable arg1) throws InterruptedException {

        List<String> raw = Arrays.asList("UK - London","British English", "AutoTestVenue ", "London") ;
        venuePage.addVenue(raw.get(0),raw.get(1),raw.get(2),raw.get(3));
    }

    @When("^I save it$")
    public void save() {
        driver.findElement(By.id("form_submit")).click();
        if (driver.findElement(By.cssSelector("BODY")).getText().contains("We have found some similar sounding events, please review them below before saving this event")){
            driver.findElement(By.id("form_submit")).click();}
        if (driver.findElement(By.cssSelector("BODY")).getText().contains("We have found some similar sounding venues, please review them below before saving this venue")){
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
        driver.findElement(By.xpath("//*[@id='column2']/ul/li[3]/a")).click();
       dashBoardPage.addTaxonomy();
    }
    @When("^I add taxonomy for Venue")
    public void addTaxonomy2() throws InterruptedException {
        //click on taxonomy link
        driver.findElement(By.xpath("//*[@id='column2']/ul/li[2]/a")).click();
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
    Assert.assertEquals("Login", driver.findElement(By.xpath("//*[@id='content']/h1")).getText());
}
// -----------------Edit Venue--------------------------------------
@Given("^I am on the Venues Page$")
public void onVenuesPage() {
    dashBoardPage.navigateToVenuesPage();
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    Assert.assertTrue(utils.isTextPresent("Venues"));
}
    @When("^I search venue with the Name '(.*)',Site '(.*)' and UpdatedInLast '(.*)'$")
    public void searchVenue(String name,String site,String UpdatedInLast) {
        venuePage.searchVenue(name, site, UpdatedInLast);
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

    @When("^I add an Event,I supply the information$")
    public void supplyEventInfo(DataTable arg1) {

        List<String> raw = Arrays.asList("British English", "Test Event", "UK - London");
        eventPage.addEvent(raw.get(2),raw.get(0),raw.get(1));

    }
    @When("^I change the Event status as '(.*)'$")
    public void changeEventStatus(String Status)
    {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        utils.selectFromDropDown(By.id("eventEdit_status"),Status);
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

    @When("^I search an Event with the Name '(.*)',Site '(.*)',status '(.*)',UpdatedInLast '(.*)'$")
    public void searchEvent(String name,String site,String status,String UpdatedInLast) {
        eventPage.searchEvent(name,site,status,UpdatedInLast);

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

    @When("^I add a Film,I supply the information$")
    public void supplyFilmInfo(DataTable arg1) {
        List<String> raw = Arrays.asList("British English", "Test Film", "Test Title", "Author", "4");
        filmsPage.addFilm(raw.get(0), raw.get(1), raw.get(2), raw.get(3), raw.get(4));
   }
    @Then("^the Film is created and should see message as '(.*)'$")
    public void filmSavedMessage(String message) {

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        Assert.assertTrue(utils.isTextPresent(message));

    }

    @When("^I add taxonomy for Film$")
    public void I_add_taxonomy_for_Film() {
      //select Taxonomy link
      driver.findElement(By.xpath("//*[@id='column2']/ul/li[5]/a")).click();
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
    filmsPage.searchFilm(film);
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

//    @When("^I add a Page,I supply the information$")
//    public void supplyPageInfo(DataTable arg1) {
//        List<String> raw = Arrays.asList("TestPageName","Test Title","Test SubTitle","UK - London","British English", "Feature");
//        pages.addPage(raw.get(0),raw.get(1),raw.get(2),raw.get(3),raw.get(4),raw.get(5));
//        }

    @When("^I add a Page,I supply '(.*)','(.*)','(.*)','(.*)','(.*)','(.*)'$")
    public void supplyPageInfo(String pageName,String title,String subTitle,String site,String language,String pageType)
    {
        pages.addPage(pageName,title,subTitle,site,language,pageType);
    }
    @Then("^I should see the '(.*)' Tab$")
    public void confirmFeatureTab(String tab)
    {
        Assert.assertTrue(driver.findElement(By.linkText(tab)).isDisplayed());
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

    @When("^I search for the Page with Keyword '(.*)',Site as '(.*)' and Status as '(.*)'$")
    public void searchForPageSite(String keyword ,String pageSite,String status) {
    pages.searchPage(keyword,pageSite,status);

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
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div[3]/div/h1")).isDisplayed());

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

 //------------Feature package superlists---------------

    @Then("^I should see Category,Super list Fields and Create list Option in Feature list tab$")
    public void featurelsitPageItems()
    {
        driver.findElement(By.linkText("Feature list")).click();
        Assert.assertTrue(utils.isTextPresent("Category"));
        Assert.assertTrue(utils.isTextPresent("Superlist"));
        Assert.assertTrue(utils.isElementPresent(By.id("category_package_01")));
        Assert.assertTrue(utils.isElementPresent(By.id("superlist_id_package_01")));
        Assert.assertTrue(utils.isElementPresent(By.id("create_new_package_01")));
    }
    @When("^I create a list, I supply category name as '(.*)' and superlist as '(.*)'$")
    public void supplySuperlistItems(String category,String superlist)
    {
        driver.findElement(By.id("category_package_01")).sendKeys(category);
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
//        List<WebElement> lstobj =  driver.findElements(By.xpath("//*[@id='category_package_01\']"));
//
//        System.out.println(lstobj.size());
//
//        for (int i = 0; i<lstobj.size();i++)
//        {
//            String p= lstobj.get(i).getText();
//            System.out.println(p);
//        }
       utils.selectFromDropDown1(By.id("ui-active-menuitem"),1);
        driver.findElement(By.id("superlist_id_package_01")).sendKeys(superlist);
        utils.selectFromDropDown1(By.id("ui-active-menuitem"),1);
    }

    @When("^Save the list$")



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
    @When("^I search a Post in blog '(.*)' and selects recently added post '(.*)'$")
    public void searchRecentPost(String blogName,String recentPost)
    {
      posts.searchPost(blogName,recentPost);
    }

   @When("^I add a Post,I supply the Information$")
    public void supplyPostInfo(DataTable arg1) {

        List<String> raw = Arrays.asList("Test Post Title","Movies","Chicago Blog (Chicago - En)","Test Body Text");
        posts.addPost(raw.get(0),raw.get(1),raw.get(2),raw.get(3));
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

