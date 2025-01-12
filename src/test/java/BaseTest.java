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
 * This is the Base class for all other derived test scenarios. It includes the setup variables
 * and set up methods accessed in every other test. Every test case implements this class as the
 * webDriver and driver methods are initialised here, along with the teardown.
 * <br/><br/>
 * The class will:<br/>
 * - Initialise the WebDriver, Baseurl, Javascript Executor and Actions variables<br/>
 * - Set up the driver options for the Selenium driver (Chrome)<br/>
 * - Navigate back to the original URL after each individual test case<br/>
 * - Quit the Selenium web driver upon completion
 * <br/><br/>
 * Additionally, any methods which are frequently used during our testing are extracted to this class and
 * provided in any descendant class.
 */
@SuppressWarnings("all")
public class BaseTest {
    // The WebDriver which enables us to detect element and run Selenium:
    protected static WebDriver webDriver;
    // The baseUrl set to: https://www.formula1.com
    protected static String baseUrl;
    // The Javascript Executor, mainly used when we have scrolling issues:
    protected static JavascriptExecutor js;
    // The Actions variable, which enables us to effectively move the cursor and perform actions if necessary:
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
        // Since both of us mostly worked on this separately, we needed to adapt the driver to the user devices:
        String currentUser = System.getProperty("user.dir");

        if(currentUser.contains("Users/hajrudin.imamovic")){
            // In case Hajrudin Imamovic is running the tests on his machine:
            System.setProperty("webdriver.chrome.driver", "/Users/hajrudin.imamovic/Documents/Drivers/chromedriver");

        } else {
            // In case Tarik Perviz is running the tests on his machine:
            System.setProperty("webdriver.chrome.driver", "C:/Users/TarikPerviz/selenium/chromedriver-win64/chromedriver.exe");
        }

        // These are some options which we tried to add to bypass the bot detection for the login and sign up tests
        // Unfortunately, nothing seemed to work so far. Here's a rundown of what we tried:
        // - Making the browser look like a human is controlling it: FAILED
        // - Using custom arguments to remove bot-like behaviour: FAILED
        // - Using a custom proxy server with node.js cors-anywhere library: FAILED
        // - Using an in-build java library: BrowserMobProxy: FAILED
        // - Run the browser in headless mode only for the log in test: FAILED
        // - Add cookies to the session to mimic logging in: FAILED

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.90 Safari/537.36");
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-features=IsolateOrigins,site-per-process");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.addArguments("--disable-extensions");
        options.addArguments("--enable-javascript");
        options.addArguments("--disable-cookie-encryption");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-component-update");
        options.addArguments("--disable-background-networking");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.formula1.com/";

        // Initially, we will always direct to the www.formula1.com webpage.
        webDriver.get(baseUrl);
        // Because we sometimes ran into the problem of some areas not loading before selenium gets to them, we decided to add this waiting setting before getting an error.
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        // In case we want to execute javascript in our test case:
        js = (JavascriptExecutor) webDriver;
        // In case we need some actions executed in our test case:
        actions = new Actions(webDriver);

        // Finally, a thread sleep will enable us to reject cookies on the main page, essential for making the tests function.
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
    /* =================================================================================== */
    /* ==== THE FOLLOWING IS A COLLECTION OF COMMONLY USED METHODS DURING OUR TESTING ==== */
    /* =================================================================================== */
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
     * Method to scroll over each element of the navbar and click on it. Will also
     * scroll by 900 to ensure the content is visible.
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
        Thread.sleep(100);
        actions.moveToElement(element).perform();
        String style_after_hover = element.getCssValue(cssProperty);
        Thread.sleep(100);
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

    /**
     *
     * Method To Help with Logging in.
     *
     * @param email The WebElement email input
     * @param password The WebElement password input
     * @param submit The WebElement that acts as the submit button
     * @param e The email we want to send
     * @param p The password we want to send
     * @throws InterruptedException In case of any errors or interruptions.
     *
     */
    @HelperMethod
    public static void logIn(WebElement email, WebElement password, WebElement submit, String e, String p) throws InterruptedException {
        email.sendKeys(e);
        Thread.sleep(1000);
        password.sendKeys(p);


        assertEquals(email.getAttribute("value"), e);
        assertEquals(password.getAttribute("value"), p);

        Thread.sleep(1000);
        submit.click();
    }

    /**
     *
     * Method to check the given article data against a specified data set. It compares on article randomly selected
     * from the UI and also proivdes the appropriate data from the DataProvider.
     *
     * @param article WebElement representing the race article itself.
     * @param data 2D Array representing the data to be used as a check. The first array is general data, the second is the race podium.
     * @throws InterruptedException in case the method is interrupted by system calls or anything related.
     * @see Utils.DataProvider
     *
     */
    @HelperMethod
    public static void raceArticleTest(WebElement article, String[][] data) throws InterruptedException {
        article.click();
        Thread.sleep(2000);

        WebElement podium_list = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/section/section/div/div/fieldset/div/div[1]/ul"));
        scrollToElement(podium_list, 0);
        List<WebElement> podium = podium_list.findElements(By.tagName("li"));
        for(int i = 0; i < 3; i++){
            assertTrue(
                    data[1][i].toUpperCase().contains(podium.get(i).findElement(By.xpath(".//span[2]/span[2]")).getText().toUpperCase()),
                    "Driver should be: " + data[1][i].toUpperCase() + " but got from the article: " + podium.get(i).findElement(By.xpath(".//span[2]/span[2]")).getText().toUpperCase()
            );
            Thread.sleep(500);
        }

        WebElement circuit_button = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/ul/li[3]/a"));
        scrollToElement(circuit_button, -100);
        circuit_button.click();
        scrollToElement(webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2")), -400);
        Thread.sleep(1000);

        String country = webDriver.findElement(By.tagName("h1")).getText();
        String circuit_name = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/legend/div/h2/div")).getText();
        String first_gp = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[1]/h2")).getText();
        String no_of_laps = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[2]/h2")).getText();
        String length = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[3]/h2")).getText();
        String lap_record = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2")).getText();
        String lap_record_holder = webDriver.findElement(By.xpath("/html/body/main/div[2]/div/div[2]/fieldset/div/div[2]/div/div[1]/div/div[5]/h2/span")).getText();

        assertEquals(data[0][0].toUpperCase(), country.toUpperCase(), "Country names should b equal");
        assertEquals(data[0][1], circuit_name, "Circuit names should be equal");
        assertEquals(data[0][2], first_gp, "Inagrual GP should be equal");
        assertEquals(data[0][3], no_of_laps, "Number of laps should be equal");
        assertTrue(data[0][4].contains(length), "The length should be as specified in the data! " + data[0][4] + " " + length);
        assertEquals(data[0][5], lap_record.substring(0, 8), "Lap record should be as specified in the data!");
        assertTrue(lap_record_holder.contains(data[0][6]), "Lap record holder should be in the data for the circuit!" + data[0][6] + " " + lap_record_holder);
        Thread.sleep(1000);
    }

    /**
     * Method to test if a given button will lead to the login page in case the user is not logged in already.
     *
     * @param button The WebElement that acts as a "Subscribe" button in the actual page.
     * @throws InterruptedException In case any errors and interruptions occur.
     */
    @HelperMethod
    public static void authorisationCheck(WebElement button) throws InterruptedException {
        scrollToElement(button, 0);
        actions.moveToElement(button).click().perform();
        Thread.sleep(1000);
        assertEquals("https://account.formula1.com/#/en/register?lead_source=web_f1core&redirect=https%253A%252F%252Faccount.formula1.com%252F%2523%252Fen%252Fsubscription", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://www.formula1.com/en-ba/subscribe-to-f1-tv");
    }

    /**
     * Method to close all window handles except the current window handle, or some other specified.
     *
     * @param currentWindow  The window handle we do NOT want to close.
     */
    @HelperMethod
    public static void closeOtherWindows(String currentWindow){
        Set<String> allWindows = webDriver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                webDriver.close();
                break;
            }
        }
    }
}
