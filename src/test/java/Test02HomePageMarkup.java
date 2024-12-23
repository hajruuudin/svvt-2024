import Utils.HelperMethod;
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


    @Test
    @Order(1)
    void testHomepageMarkup() throws InterruptedException {
        List<String> headingTitles = Arrays.asList("Editor's Picks", "More news", "Driver Standings", "Constructor Standings", "Abu Dhabi", "Explore F1 topics");

        assertEquals("https://www.formula1.com/", webDriver.getCurrentUrl());

        assertEquals("F1 - The Official Home of Formula 1® Racing", webDriver.getTitle());

//        List<WebElement> heading2s = webDriver.findElements(By.tagName("h2"));
//        heading2s.remove(0);
////        ERROR FOUND HERE: Two Elements are extra
//        for(WebElement h2 : heading2s){
//            assertTrue(headingTitles.contains(h2.getText()), "The heading scanned: " + h2.getText() + " should be included inside the array!");
//        }

        WebElement standings_navbar = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/ul"));
        scrollToElement(standings_navbar, -200);
        List<WebElement> navbar_nav = standings_navbar.findElements(By.tagName("li"));

        navbar_nav.get(0).click();
        WebElement driver_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/div[1]/div/h2"));
        assertEquals("Driver Standings", driver_standings.getText());
        Thread.sleep(500);

        navbar_nav.get(1).click();
        WebElement constructor_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/div[2]/div/h2"));
        assertEquals("Constructor Standings", constructor_standings.getText());
        Thread.sleep(500);

        navbar_nav.get(2).click();
        WebElement lastrace_standings = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/div[3]/div/div[1]/h2"));
        assertEquals("ABU DHABI", lastrace_standings.getText());
        Thread.sleep(500);
    }

    @Test
    @Order(2)
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

        WebElement driver_list = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/div[1]/div/div/div[2]/ul"));
        scrollToElement(driver_list, -200);
        List<WebElement> drivers = driver_list.findElements(By.tagName("li"));
        for(WebElement da : drivers){
            styleTesting(
                    da.findElement(By.tagName("a")),
                    "background-color",
                    false );
        }

        WebElement footer_list = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[3]/div/fieldset/ul"));
        scrollToElement(footer_list, -100);
        List<WebElement> footer_tabs = footer_list.findElements(By.tagName("li"));
        for(WebElement ft : footer_tabs){
            styleTesting(
                    ft.findElement(By.tagName("a")).findElement(By.tagName("figure")),
                    "opacity",
                    false);
        }
    }

    @Test
    @Order(3)
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
            WebElement drivers_container = webDriver.findElement(By.xpath("/html/body/main/div[4]/div[1]/div[1]/div/div/div[2]/ul"));
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
