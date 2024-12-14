import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 *
 * <b><font color="red">CRITICAL</font>: Navigation, Inputs & Accessibility:</b> These tests will ensure the navigation works properly
 * on the webpage, ensuring that all hyperlinks lead to where they are supposed to lead
 * based on their description, buttons are clickable and modify the page appropriately,
 * the UI of the application is consistent with the data shown… Essentially, we test to
 * ensure the application has proper navigation.
 *
 */
public class NavigationTests {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    static void setUp(){
        String currentUser = System.getProperty("user.dir");
        System.out.println(currentUser);

        if(currentUser.contains("Users/hajrudin.imamovic")){
            // In case Hajrudin Imamovic is running the tests on his machine
            System.setProperty("webdriver.gecko.driver", "/Users/hajrudin.imamovic/Documents/Drivers/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            webDriver = new FirefoxDriver(options);
        } else {
            // In case Tarik Perviz is running the tests on his machine
            System.out.println("Ovde ti svoj driver i options daj!");
        }

        baseUrl = "https://www.formula1.com/";
    }

    /**
     * Test Scenario Example: Here we want to open the website, ensure we see the homepage, navigate
     * to the race calendar and navigate back to the homepage
     */
    @Nested
    class TestOpenAndNavigate{
        @Test
        void testOpenHomepage(){/* code */}

        @Test
        void testNavigateToRaceCalendar(){}

        @Test
        void testRaceArticle(){}
    }

    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
