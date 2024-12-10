package TestScenarios;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Example class: The scenarios here are related to opening, navigating,
 * going around the entire web page and making sure no errors or bugs
 * appear as we navigate over the entire webpage
 *
 */
public class TestOpening {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    static void setUp(){
        String currentUser = System.getProperty("user.dir");
        System.out.println(currentUser);

        if(currentUser.contains("Users/hajrudin.imamovic")){
            System.setProperty("webdriver.gecko.driver", "/Users/hajrudin.imamovic/Documents/Drivers/geckodriver");
            FirefoxOptions options = new FirefoxOptions();
            webDriver = new FirefoxDriver(options);
        } else {
            System.out.println("Ovde ti svoj driver i options daj!");
        }

        baseUrl = "https://www.formula1.com/";
    }

    /**
     *
     * Example Scenario: The page should be opened with the specified URL, the title
     * of the page should be retrieved
     *
     */
    @Test
    public void testOpeningPage(){
        webDriver.get(baseUrl);
        String url = webDriver.getCurrentUrl();
        String title = webDriver.getTitle();

        assertEquals(baseUrl, url);
        assertEquals("F1 - The Official Home of Formula 1® Racing", title);
    }

    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
