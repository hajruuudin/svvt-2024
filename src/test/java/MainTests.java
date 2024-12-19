import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * <b><font color="white">MAIN TESTS</font></b>: This is the Home website that we are taken
 * to once the url www.formula1.com is clicked. This contains the main data and news related
 * articles related to F1 and is treated as the main site, acting as the entry point to the entire
 * Web Application. We will test this site in detail, providing explanations for what
 * we are testing and why.
 *
 */
public class MainTests {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static JavascriptExecutor js;

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
        js = (JavascriptExecutor) webDriver;
        Thread.sleep(3000);
    }

    /**
     * <b> <font color="red">TEST 01 - CRITICAL</font> </b> <br/>
     * <strong/> Category: Navigation & UI </strong> <br/>
     * <strong> Testing the main navigation bar and its links </strong> <br/>
     * This test should cover the main navigation bar of the homepage. Then, it should
     * also assert that all the navigation bar links are clickable, shown, functional and
     * that they change their style properly once hovered. Each navigation bar link should be
     * clicked and ensure that the URL is changed and properly navigated. We should also assert
     * that we can scroll through the page and ensure at least one main element of the page is
     * properly displayed. This test covers design and functionality bugs.
     */
    @Nested
    class TestMainNavbar{
        private void scrollAndClick(String element_id) throws InterruptedException {
            WebElement navbar_link = webDriver.findElement(By.id(element_id));
            navbar_link.click();
            Thread.sleep(500);
            js.executeScript("window.scrollBy(0, 900)");
            Thread.sleep(500);
        }

        // Test Case: Open the first nav link and test its initial content
        @Test
        void testNavigationLatest() throws InterruptedException {
            WebElement navbar_latest = webDriver.findElement(By.id("Latest"));
            navbar_latest.click();
            assertEquals("https://www.formula1.com/en/latest", webDriver.getCurrentUrl());
            Thread.sleep(500);

            WebElement firstH2 = webDriver.findElement(By.xpath("/html/body/main/section[1]/section[2]/fieldset/legend/h2"));
            assertEquals("Top Stories", firstH2.getText());
            Thread.sleep(500);

            WebElement firstArticle = webDriver.findElement(By.xpath("/html/body/main/section[1]/section[2]/fieldset/section/ul[1]/li/a"));
            firstArticle.click();
            assertTrue(webDriver.getCurrentUrl().contains("https://www.formula1.com/en/latest/article"));
            Thread.sleep(500);
        }

        // Test Case: Going over the navbar and its links
        @Test
        void testNavigateOverNavbar() throws InterruptedException {

            WebElement navbar_ul = webDriver.findElement(By.xpath("/html/body/header/section[3]/nav/ul"));
            List<WebElement> navbar_links = navbar_ul.findElements(By.tagName("li"));
            List<String> navbar_link_ids = new ArrayList<>();

            for(WebElement link : navbar_links){
                navbar_link_ids.add(link.getText());
            }

            for(String link_id : navbar_link_ids){
                scrollAndClick(link_id);
            }

        }

        // Test Navigating in the navbar while it is collapsed.
        @Test
        void testCollapsedNavbar() throws InterruptedException {
            webDriver.manage().window().setSize(new Dimension(800, 796));
            Thread.sleep(500);

            webDriver.navigate().refresh();
            Thread.sleep(500);

            WebElement menu_button = webDriver.findElement(By.xpath("/html/body/header/section[1]/button"));
            menu_button.click();

            WebElement nav_link = webDriver.findElement(By.id("Gaming"));
            nav_link.click();
            assertEquals("https://www.formula1.com/en/page/gaming", webDriver.getCurrentUrl());
        }
    }

    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }
}
