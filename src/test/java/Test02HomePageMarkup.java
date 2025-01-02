import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 02 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the homepage and its markup / content </strong> <br/>
 * This test should cover the homepage of the Formula 1 website. It should ensure basic markup is correct on every
 * test, meaning al headings, paragraphs, formating is done properly and shown on the whole Homepage.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test02HomePageMarkup extends BaseTest {

    @Order(1)
    @Test /* Test the homepage markup, like the title, headings and the URL and changes on the website */
    void testHomepageMarkup() throws InterruptedException {
        assertEquals("https://www.formula1.com/", webDriver.getCurrentUrl());

        assertEquals("F1 - The Official Home of Formula 1® Racing", webDriver.getTitle());

        WebElement standings_navbar = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/ul"));
        scrollToElement(standings_navbar, -200);
        List<WebElement> navbar_nav = standings_navbar.findElements(By.tagName("li"));

        navbar_nav.get(0).click();
        WebElement driver_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div[1]/div/h2"));
        assertEquals("2024 Driver Standings", driver_standings.getText());
        Thread.sleep(500);

        navbar_nav.get(1).click();
        WebElement constructor_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div[2]/div/h2"));
        assertEquals("2024 Constructor Standings", constructor_standings.getText());
        Thread.sleep(500);

        navbar_nav.get(2).click();
        WebElement lastrace_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div[3]/div/div[1]/h2"));
        assertEquals("ABU DHABI", lastrace_standings.getText());
        Thread.sleep(500);
    }

    @Order(2)
    @Test /* Test the homepage styles, hovering, changes */
    void testHomepageStyles() throws InterruptedException {
        WebElement featured_container = webDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div[2]/div"));
        scrollToElement(featured_container, -200);
        List<WebElement> featured_articles = featured_container.findElements(By.tagName("li"));
        for(WebElement fa : featured_articles){
            styleTesting(
                    fa,
                    "background-color",
                    true);
        }

        WebElement more_news_container = webDriver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div/div[2]"));
        scrollToElement(more_news_container, -100);
        List<WebElement> articles = more_news_container.findElements(By.tagName("li"));
        for(WebElement a : articles){
            styleTesting(
                    a,
                    "background-color",
                    false);
        }

        WebElement driver_list = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div[1]/div/div/div[2]/ul"));
        scrollToElement(driver_list, -200);
        List<WebElement> drivers = driver_list.findElements(By.tagName("li"));
        for(WebElement da : drivers){
            styleTesting(
                    da.findElement(By.tagName("a")),
                    "background-color",
                    false );
        }

        WebElement footer_list = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[4]/div/fieldset/ul"));
        scrollToElement(footer_list, -100);
        List<WebElement> footer_tabs = footer_list.findElements(By.tagName("li"));
        for(WebElement ft : footer_tabs){
            styleTesting(
                    ft.findElement(By.tagName("a")).findElement(By.tagName("figure")),
                    "opacity",
                    false);
        }
    }

    @Order(3)
    @Test /* Test homepage navigation for articles in the latest, more news and drivers section */
    void testHomepageNavigation() throws InterruptedException {
        Random rand = new Random();

        WebElement featured_container = webDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div[2]/div"));
        scrollToElement(featured_container, -200);
        List<WebElement> featured_articles = featured_container.findElements(By.tagName("li"));
        WebElement featured_a = featured_articles.get(rand.nextInt(7));
        articleTesting(
                featured_a,
                ".//a/div/figcaption/div/p",
                ".//a/div/figcaption/div/span",
                true,
                "/html/body/main/section[1]/section/div/div[1]/section/p/span"
        );

        WebElement news_container = webDriver.findElement(By.xpath("/html/body/main/div[3]/div[1]/div/div[2]"));
        scrollToElement(news_container, -200);
        List<WebElement> news_articles = news_container.findElements(By.tagName("li"));
        WebElement news_a = news_articles.get(rand.nextInt(4));
        articleTesting(
                news_a,
                ".//a/figcaption/p",
                ".//a/figcaption/span",
                true,
                "/html/body/main/section[1]/section/div/div[1]/section/p/span"
        );

        for(int i = 0; i < 4; i++){
            WebElement drivers_container = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div[1]/div/div/div[2]/ul"));
            scrollToElement(drivers_container, -200);
            List<WebElement> driver_cards = drivers_container.findElements(By.tagName("li"));
            WebElement driver_card = driver_cards.get(rand.nextInt(4));
            articleTesting(
                    driver_card,
                    ".//a/span/span[1]",
                    "NOT DEFINED",
                    false,
                    "NOT DEFINED"
            );
        }
    }
}
