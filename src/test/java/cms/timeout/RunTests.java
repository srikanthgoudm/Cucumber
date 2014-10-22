package cms.timeout;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by Sairam on 11/08/2014.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"html:target/cucumber"},tags ={"@addBlog"})
//        "@Venue_New")
public class RunTests {
}
