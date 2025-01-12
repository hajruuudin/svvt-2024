import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 04 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing articles and their markup </strong> <br/>
 * This scenario should cover article on the F1.com page, making sure that each article has a consistent
 * and functional display. Each articles content is checked, including the title, tag, content, button and
 * their functionality & display / styling.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
public final class Test04PageArticles extends BaseTest {
    @Order(1)
    @Test/* Test navigating to an article and comparing the Heading and Tag of the article with the Actual Page */
    void testArticleConsistency() throws InterruptedException {
        WebElement featured_container = webDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div[2]/div"));
        scrollToElement(featured_container, -200);
        List<WebElement> featured_articles = featured_container.findElements(By.tagName("li"));
        WebElement featured_a = featured_articles.get(0);
        articleTesting(
                featured_a,
                ".//a/div/figcaption/div/p",
                ".//a/div/figcaption/div/span",
                true,
                "/html/body/main/section[1]/section/div/div[1]/section/p/span"
        );
    };

    @Order(2)
    @Test /* Test styling the tags, their response and if their navigation is consistent with the intent */
    void testArticleTags() throws InterruptedException {
        WebElement featured_container = webDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div[2]/div"));
        scrollToElement(featured_container, -200);
        List<WebElement> featured_articles = featured_container.findElements(By.tagName("li"));
        WebElement featured_a = featured_articles.get(0);
        featured_a.click();

        WebElement article_tags_list = webDriver.findElement(By.xpath("/html/body/main/section[1]/section/section[1]/ul"));
        List<WebElement> article_tags = article_tags_list.findElements(By.tagName("li"));
        article_tags.removeLast();
        for(WebElement tag : article_tags){
            styleTesting(
                    tag.findElement(By.tagName("a")),
                    "box-shadow",
                    false
            );
        }

        int number_of_tags = article_tags.size();
        for(int i = 0; i < number_of_tags; i++){
            WebElement tags_list = webDriver.findElement(By.xpath("/html/body/main/section[1]/section/section[1]/ul"));
            List<WebElement> tags = tags_list.findElements(By.tagName("li"));
            tags.removeLast();
            articleTesting(
                    tags.get(i).findElement(By.tagName("a")),
                    ".//span",
                    "NOT DEFINED",
                    false,
                    "FALSE"
            );
        }
    };

    @Order(3)
    @Test /* Test the share buttons and if they navigate to the proper URL indicated by the article */
    void testArticleShareButtons() throws InterruptedException {
        WebElement featured_container = webDriver.findElement(By.xpath("/html/body/main/div[2]/div[2]/div[2]/div"));
        scrollToElement(featured_container, -200);
        List<WebElement> featured_articles = featured_container.findElements(By.tagName("li"));
        WebElement featured_a = featured_articles.get(0);
        featured_a.click();

        WebElement button_container = webDriver.findElement(By.xpath("/html/body/main/section[3]/div/section/div"));
        scrollToElement(button_container, -400);

        String article_query = webDriver.getCurrentUrl().substring(41);
        WebElement facebook = button_container.findElement(By.xpath(".//button[1]"));
        WebElement twitter = button_container.findElement(By.xpath(".//button[2]"));

        String originalWindow = webDriver.getWindowHandle();

        // Test clicking the facebook icon, then compare the URL with the article URL
        facebook.click();
        Thread.sleep(2000);
        switchWindow(originalWindow);
        assertTrue(
                webDriver.getCurrentUrl().contains(article_query),
                "The intent of the current query should contain the previous article!"
        );
        webDriver.switchTo().window(originalWindow);

        // Test clicking the twitter icon, then compare the URL with the article URL
        twitter.click();
        Thread.sleep(2000);
        switchWindow(originalWindow);
        assertTrue(
                webDriver.getCurrentUrl().contains(article_query),
                "The intent of the current query should contain the previous article!"
        );
        webDriver.switchTo().window(originalWindow);

    };

}
