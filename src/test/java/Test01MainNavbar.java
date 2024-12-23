import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
public class Test01MainNavbar extends BaseTest {

    /**
     *
     * Method to scroll over each element of the navbar. Specific to this class of testing.
     *
     * @param element_id The id of the navbar element
     * @throws InterruptedException if the thread is interrupted during execution.
     */
    private void scrollAndClick(String element_id) throws InterruptedException {
        WebElement navbar_link = webDriver.findElement(By.id(element_id));
        navbar_link.click();
        Thread.sleep(500);
        js.executeScript("window.scrollBy(0, 900)");
        Thread.sleep(500);
    }

    // Test Case: Open the first nav link and test its initial content
    @Test
    @Order(1)
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

        webDriver.navigate().to(baseUrl);
    }

    // Test Case: Going over the navbar and its links
    @Test
    @Order(2)
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

    // Test Navigating in the navbar while it is collapsed.
    @Test
    @Order(3)
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
