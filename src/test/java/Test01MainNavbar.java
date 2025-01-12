import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
public class Test01MainNavbar extends BaseTest {
    @Order(1)
    @Test /* Open the first nav link and test its initial content */
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
        Thread.sleep(500);
        assertTrue(webDriver.getCurrentUrl().contains("https://www.formula1.com/en/latest/article"));
        Thread.sleep(500);

        webDriver.navigate().to(baseUrl);
    }

    @Order(2)
    @Test /* Going over the navbar and its links */
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

        webDriver.navigate().to(baseUrl);
    }

    @Order(3)
    @Test /* Navigating in the navbar while it is collapsed. */
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
