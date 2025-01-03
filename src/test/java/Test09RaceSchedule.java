import Utils.DataProvider;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 09 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the race schedule page </strong> <br/>
 * This scenario pretty much goes over the Race Schedule page, going mainly over its data
 * and consistency, along with the UI. It should assert that the data is set up for
 * proper navigation and that the data is accurate on all races.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test09RaceSchedule extends BaseTest{
    @Order(1)
    @Test /* Test to cover if the dropdown navigation is functional in the "Schedule" section */
    void testRaceScheduleNavbar() throws InterruptedException {
        WebElement schedule_link = webDriver.findElement(By.id("Schedule"));
        schedule_link.click();

        assertEquals("https://www.formula1.com/en/racing/2024", webDriver.getCurrentUrl());

        actions.moveToElement(schedule_link);
        Thread.sleep(1000);

    }

    @Order(2)
    @Test /* Test to see if the styling changes for all race articles */
    void testRaceScheduleStyling() throws InterruptedException {
        // For demonstration and consistency purposes, we are testing the article for the 2021 season
        // Because F1 constantly updates its website, we have to adjust and not test the current season
        webDriver.navigate().to("https://www.formula1.com/en/racing/2021");
        WebElement articles_container = webDriver.findElement(By.xpath("/html/body/main/div/div[1]/div[3]/div"));
        List<WebElement> articles = articles_container.findElements(By.tagName("a"));
        for(WebElement a : articles){
            styleTesting(
                    a.findElement(By.tagName("fieldset")),
                    "margin",
                    false

            );
        }

    }

    @Order(3)
    @Test /* Test to see if any given race schedule article is consistent and functional */
    void testRaceScheduleMarkup() throws InterruptedException {
        // For demonstration and consistency purposes, we are testing the article for the 2021 season
        // Because F1 constantly updates its website, we have to adjust and not test the current season
        webDriver.navigate().to("https://www.formula1.com/en/racing/2021");
        WebElement articles_container = webDriver.findElement(By.xpath("/html/body/main/div/div[1]/div[3]/div"));
        List<WebElement> articles = articles_container.findElements(By.tagName("a"));
        List<WebElement> filtered_articles =  articles.stream().filter(article -> article.findElement(By.xpath(".//fieldset/legend/p")).getText().contains("ROUND")).toList();

        Random rand = new Random();
        int round = rand.nextInt(1, 23);

        // The article used for checking:
        WebElement random_article = filtered_articles.get(round - 1);
        // The data that we will check:
        String[][] data = DataProvider.f1_2021_season[round];

        Thread.sleep(1000);
        raceArticleTest(random_article, data);

    }

}
