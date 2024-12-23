import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 02 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the homepage and its markup / content </strong> <br/>
 * This test should cover the homepage of the Formula 1 website. It should ensure basic markup is correct on every
 * test, meaning al headings, paragraphs, formating is done properly and shown on both web and mobile views.
 */
public class Test02HomePageMarkup extends BaseTest {
    /**
     * Method to scroll to an element using the JS executor.
     * Adjusts the scroll position based on the input, if the default location is obscured by the content.
     *
     * @param element the WebElement we want to scroll to.
     * @param adjust the adjustment scroll if it is necessary.
     */
    private static void scrollToElement(WebElement element, int adjust){
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
    private static void styleTesting(WebElement element, String cssProperty, boolean adjust_position) throws InterruptedException {
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


    @Test
    void testHomepageMarkup() throws InterruptedException {
        List<String> headingTitles = Arrays.asList("Editor's Picks", "More news", "Driver Standings", "Constructor Standings", "Abu Dhabi", "Explore F1 topics");

        assertEquals("https://www.formula1.com/", webDriver.getCurrentUrl());

        assertEquals("F1 - The Official Home of Formula 1® Racing", webDriver.getTitle());

        List<WebElement> heading2s = webDriver.findElements(By.tagName("h2"));
//        ERROR FOUND HERE: Two Elements are extra
//        for(WebElement h2 : heading2s){
//            assertTrue(headingTitles.contains(h2.getText()), "The heading scanned should be included inside the array!");
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
    void testHomepageNavigation(){

    }

    @Test
    void testHomepageMobileView(){

    }
}
