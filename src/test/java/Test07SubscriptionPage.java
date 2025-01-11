import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 07 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1TV Subscription Page </strong> <br/>
 * This test should cover the F1TV subscription page and its semantics, like markup, navigation functionality and
 * button styling, UI changes...
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test07SubscriptionPage extends BaseTest{
    // TO-DO
    @Order(1)
    @Test
    void testPageMarkup() throws InterruptedException {
        WebElement subscribe_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[2]"));
        subscribe_button.click();
        Thread.sleep(500);
        String page_title = webDriver.getTitle();
        assertEquals("Stream Formula 1 live | F1 TV", page_title);

        WebElement subscriptionHeading = webDriver.findElement(By.tagName("h1"));
        assertNotNull(subscriptionHeading);
        assertEquals("STREAM F1 LIVE, YOUR WAY", subscriptionHeading.getText());

        Actions actions = new Actions(webDriver);
        WebElement section = webDriver.findElement(By.id("products-mini-parent"));
        actions.moveToElement(section).perform();
        Thread.sleep(500);

        WebElement planSwitchButton = webDriver.findElement(By.xpath("/html/body/div/div/main/section/section[2]/div[2]/div[1]/label/div/button"));
        WebElement durationText = webDriver.findElement(By.xpath("/html/body/div/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/div[1]/div/span[2]"));
        String initialText= durationText.getText().trim();
        assertEquals("/ Year", initialText);
        planSwitchButton.click();
        Thread.sleep(500);
        durationText = webDriver.findElement(By.xpath("/html/body/div/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/div[1]/div/span[2]"));
        String updatedText = durationText.getText().trim();
        assertEquals("/ Month", updatedText);

        section = webDriver.findElement(By.id("watch-this-season"));
        actions.moveToElement(section).perform();

        WebElement subscriptionButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/a"));
        assertTrue(subscriptionButton.isDisplayed());

        String backgroundColor = subscriptionButton.getCssValue("background-color");
        assertEquals("rgba(225, 6, 0, 1)", backgroundColor);


        actions.moveToElement(subscriptionButton).perform();
        Thread.sleep(500);
        String hoverColor = subscriptionButton.getCssValue("background-color");
        assertEquals("rgba(255, 255, 255, 1)", hoverColor);

        section = webDriver.findElement(By.id("live-timing"));
        actions.moveToElement(section).perform();

        WebElement buttonIcon = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[1]/button[5]"));
        actions.moveToElement(buttonIcon).perform();
        buttonIcon.click();
        Thread.sleep(100);
        WebElement buttonIconText = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[6]/p[1]"));
        assertEquals("TYRE USAGE HISTORY", buttonIconText.getText());

        buttonIcon=webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[1]/button[4]"));
        actions.moveToElement(buttonIcon).perform();
        buttonIcon.click();
        Thread.sleep(100);
        buttonIconText = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[5]/p[1]"));
        assertEquals("LIVE DRIVER MAPS", buttonIconText.getText());

        buttonIcon=webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[1]/button[3]"));
        actions.moveToElement(buttonIcon).perform();
        buttonIcon.click();
        Thread.sleep(100);
        buttonIconText = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[4]/p[1]"));
        assertEquals("BEST CLIPS OF TEAM RADIO", buttonIconText.getText());

        buttonIcon=webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[1]/button[2]"));
        actions.moveToElement(buttonIcon).perform();
        buttonIcon.click();
        Thread.sleep(100);
        buttonIconText = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[3]/p[1]"));
        assertEquals("REAL TIME TELEMETRY", buttonIconText.getText());

        buttonIcon=webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[1]/button[1]"));
        actions.moveToElement(buttonIcon).perform();
        buttonIcon.click();
        Thread.sleep(100);
        buttonIconText = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[4]/div[2]/div/div/div/div[3]/div[2]/p[1]"));
        assertEquals("LIVE LEADERBOARD DATA", buttonIconText.getText());
    };

    @Order(2)
    @Test
    void testPageNavigation() throws InterruptedException{
        WebElement subscribe_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[2]"));
        subscribe_button.click();
        Thread.sleep(2000);

        WebElement subscriptionButton = webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/a"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(subscriptionButton).perform();
        subscriptionButton.click();
        Thread.sleep(2000);
        assertEquals("https://account.formula1.com/#/en/register?lead_source=web_f1core&redirect=https%253A%252F%252Faccount.formula1.com%252F%2523%252Fen%252Fsubscription", webDriver.getCurrentUrl());

        WebElement loginButton= webDriver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/ul/li[1]/a"));
        loginButton.click();
        Thread.sleep(500);
        assertEquals("https://account.formula1.com/#/en/login?lead_source=web_f1core&redirect=https%253A%252F%252Faccount.formula1.com%252F%2523%252Fen%252Fsubscription", webDriver.getCurrentUrl());


    };
}
