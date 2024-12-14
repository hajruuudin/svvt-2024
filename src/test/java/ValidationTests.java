import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 *
 * <b><font color="red">CRITICAL</font>: Authentication, Session & Metadata:</b> These tests
 * will ensure that the session data and the authentication section of the application works,
 * along with other aspects which are not directly correlated to the UI are properly set up and
 * work as expected. Essentially, we make sure that parts of the application which are related to
 * security protocols, consistency and authorization are properly initialized and valid.
 *
 */
public class ValidationTests {
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

    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
