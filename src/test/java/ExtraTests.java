import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 *
 * <b><font color="white">EXTRA</font>: Other Important Sections:</b> This is just a bonus
 * section of a couple of tests related to visiting sister sites to formula1.com and ensuring
 * they are structured properly and work as expected. They don’t exactly test formula1.com itself,
 * but since all of these websites are in one way or another related to our main website, we decided
 * it is important to include these small tests as well.
 *
 */
public class ExtraTests {
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
