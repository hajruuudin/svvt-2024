import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {
    protected static WebDriver webDriver;
    protected static String baseUrl;
    protected static JavascriptExecutor js;
    protected static Actions actions;

    /**
     *
     * This is the setup done initially before all tests are started.
     * Basically set the baseUrl and the driver for each user.
     * Depending on who is running the tests, we use the appropriate driver.
     *
     */
    @BeforeAll
    static void setUp() throws InterruptedException {
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
        webDriver.get(baseUrl);
        // Because we sometimes ran into the problem of some areas not loading before
        // selenium gets to them, we decided to add this waiting setting before adding an error.
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        webDriver.manage().window().maximize();
        // In case we want to execute javascript in our test case:
        js = (JavascriptExecutor) webDriver;
        // In case we need some actions executed in our test case:
        actions = new Actions(webDriver);
//        FIX THIS!!!
//        String currentTime = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
//        Cookie consentCookie = new Cookie.Builder("consentDate", currentTime)
//                .domain(".formula1.com")
//                .path("/")
//                .isSecure(true)
//                .build();
//        webDriver.manage().addCookie(consentCookie);
//
//        Cookie consentUUIDCookie = new Cookie.Builder("consentUUID", currentTime)
//                .domain(".formula1.com")
//                .path("/")
//                .isSecure(true)
//                .build();
//        webDriver.manage().addCookie(consentUUIDCookie);

        Thread.sleep(2000);
    }

    @AfterEach
    void returnToHome(){
        webDriver.navigate().to(baseUrl);
    }

    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
