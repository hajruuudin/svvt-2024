import Utils.HelperMethod;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * BaseTest is the foundational class for all test classes in the project.
 * It provides shared setup, teardown, and utility methods to facilitate
 * the execution and validation of Selenium-based tests.
 */
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
//            System.setProperty("webdriver.gecko.driver", "/Users/hajrudin.imamovic/Documents/Drivers/geckodriver");
//            FirefoxOptions options = new FirefoxOptions();
//            webDriver = new FirefoxDriver(options);
            System.setProperty("webdriver.chrome.driver", "/Users/hajrudin.imamovic/Documents/Drivers/chromedriver");
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--remote-allow-origins=*");
            options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.90 Safari/537.36");

            webDriver = new ChromeDriver(options);
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


        Thread.sleep(3000);
    }

    /**
     * This is done after each test is individually completed. We return to the base URL.
     * Because many test initially start from the base URL, we return to the base every time.
     */
    @AfterEach
    void returnToHome(){
        webDriver.navigate().to(baseUrl);
    }

    /**
     * After each test scenario is completed, we quit the Selenium web driven windows.
     */
    @AfterAll
    static void tearDown(){
        if(webDriver != null){
            webDriver.quit();
        }
    }

    /* =================================================================================== */
    /* ==== THE FOLLOWING IS A COLLECTION OF COMMONLY USED METHODS DURING OUR TESTING ==== */
    /* =================================================================================== */

    /**
     * Method to scroll to an element using the JS executor.
     * Adjusts the scroll position based on the input, if the default location is obscured by the content.
     *
     * @param element the WebElement we want to scroll to.
     * @param adjust the adjustment scroll if it is necessary.
     */
    @HelperMethod
    public static void scrollToElement(WebElement element, int adjust){
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        if(adjust != 0){
            js.executeScript("window.scrollBy(0, " + adjust +")");
        }
    }

    /**
     *
     * Method to scroll over each element of the navbar. Specific to this class of testing.
     *
     * @param element_id The id of the navbar element
     * @throws InterruptedException if the thread is interrupted during execution.
     */
    public void scrollAndClick(String element_id) throws InterruptedException {
        WebElement navbar_link = webDriver.findElement(By.id(element_id));
        navbar_link.click();
        Thread.sleep(500);
        js.executeScript("window.scrollBy(0, 900)");
        Thread.sleep(500);
    }

    /**
     * This method tests the change in a specified CSS property of a WebElement before and after hovering over it.
     * It checks if the CSS property of the element changes when the mouse hovers over it, and asserts that the styles are different.
     * Optionally, it can scroll the element into view before performing the hover action.
     *
     * @param element the WebElement on which the hover action is performed and style is checked.
     * @param cssProperty the CSS property whose value is checked before and after the hover action.
     * @param adjust_position if true, scrolls the element into view before performing the hover action.
     * @throws InterruptedException if the thread is interrupted during the sleep intervals.
     */
    @HelperMethod
    public static void styleTesting(WebElement element, String cssProperty, boolean adjust_position) throws InterruptedException {
        if(adjust_position){
            scrollToElement(element, -100);
        }
        String style_before_hover = element.getCssValue(cssProperty);
        Thread.sleep(300);
        actions.moveToElement(element).perform();
        String style_after_hover = element.getCssValue(cssProperty);
        Thread.sleep(300);
        assertNotEquals(style_before_hover, style_after_hover, "Styles should change on hover");
    }

    /**
     *
     * Method to test article navigation and display. Should navigate to the article and assert that the heading (and
     * optionally tag) of the article is consistent with the actual page.
     *
     * @param article WebElement representing the article to be clicked.
     * @param target_title_path String representing the name of the article on the homepage.
     * @param article_tag_path String representing the name of the tag if it is present on the homepage.
     * @param hasTag Boolean representing if the tag is present on the original article link.
     * @param target_tag_path String representing the path of the tag on the target page.
     * @throws InterruptedException if the thread is interrupted during execution due to various reasons.
     *
     */
    @HelperMethod
    public static void articleTesting(WebElement article, String target_title_path, String target_tag_path, boolean hasTag, String article_tag_path) throws InterruptedException {
        String target_title = "";
        String target_tag = "";
        String article_tag = "";
        target_title = article.findElement(By.xpath(target_title_path)).getText();
        if(hasTag){
            target_tag = article.findElement(By.xpath(target_tag_path)).getText();
        }
        article.click();
        Thread.sleep(500);
        String article_headline = webDriver.findElement(By.tagName("h1")).getText();
        if(hasTag){
            article_tag = webDriver.findElement(By.xpath(article_tag_path)).getText();
        }
        assertEquals(article_headline.toUpperCase(), target_title.toUpperCase(), "Headline from Homepage article and actual article should match!");
        if(hasTag){
            assertEquals(article_tag.toUpperCase(), target_tag.toUpperCase(), "Headline from Homepage article and actual article should match!");
        }
        Thread.sleep(500);
        webDriver.navigate().back();
        Thread.sleep(500);
    }

    /**
     *
     * Function to switch to the first available window excluding the main window.
     *
     * @param originalWindow the String od the original window moved from.
     *
     */
    public static void switchWindow(String originalWindow){
        Set<String> allWindows = webDriver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                webDriver.switchTo().window(window);
                break;
            }
        }
    }

    @HelperMethod
    public static void logIn(WebElement email, WebElement password, WebElement submit, String e, String p) throws InterruptedException {
        email.sendKeys(e);
        Thread.sleep(1000);
        password.sendKeys(p);


        assertEquals(email.getAttribute("value"), e);
        assertEquals(password.getAttribute("value"), p);

        Thread.sleep(10000);
        submit.click();
    }

    @HelperMethod
    public static void raceArticleTest(WebElement article, String[][] data) throws InterruptedException {
        article.click();
        Thread.sleep(2000);
//        assertEquals("https://www.formula1.com/en/racing/2021/" + data[0][0], webDriver.getCurrentUrl());


        WebElement podium_list = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/section/section/div/div/fieldset/div/div[1]/ul"));
        scrollToElement(podium_list, 0);
        List<WebElement> podium = podium_list.findElements(By.tagName("li"));
        for(int i = 0; i < 3; i++){
            System.out.println(i);
            assertTrue(
                    data[1][i].toUpperCase().contains(podium.get(i).findElement(By.xpath(".//span[2]/span[2]")).getText().toUpperCase()),
                    "Driver should be: " + data[1][i].toUpperCase() + " but got from the article: " + podium.get(i).findElement(By.xpath(".//span[2]/span[2]")).getText().toUpperCase()
            );
            Thread.sleep(500);
        }

        WebElement circuit_button = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/ul/li[3]/a"));
        scrollToElement(circuit_button, -100);
        circuit_button.click();
        scrollToElement(webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2")), 0);
        Thread.sleep(1000);

        String country = webDriver.findElement(By.tagName("h1")).getText();
        String circuit_name = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/legend/div/h2/div")).getText();
        String first_gp = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[1]/h2")).getText();
        String no_of_laps = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[2]/h2")).getText();
        String length = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[3]/h2")).getText();
        String lap_record = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2")).getText();
        String lap_record_holder = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2/span")).getText();

        assertEquals(data[0][0].toUpperCase(), country.toUpperCase());
        assertEquals(data[0][1], circuit_name);
        assertEquals(data[0][2], first_gp);
        assertEquals(data[0][3], no_of_laps);
        assertTrue(data[0][4].contains(length));
        assertEquals(data[0][5], lap_record);
        assertTrue(data[0][6].contains(lap_record_holder));
        Thread.sleep(1000);
    }
}
