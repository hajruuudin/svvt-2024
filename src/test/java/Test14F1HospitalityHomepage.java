import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.Iterator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <b> <font color="white">TEST 14 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store Homepage </strong> <br/>
 * This test should cover the footer on the F1 Store Homepage, ensuring that all the markup is as expected
 * all the links work and the styling is appropriate.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
public class Test14F1HospitalityHomepage extends BaseTest {
    @Order(1)
    @Test /* Test the F1 hospitality page markup and its content */
    void testHomepageMarkup() throws InterruptedException {
        WebElement nav_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[4]/a"));
        nav_button.click();
        Set<String> windowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String originalTab = iterator.next();
        String newTab = iterator.next();
        webDriver.switchTo().window(newTab);
        webDriver.switchTo().window(originalTab);
        webDriver.close();
        webDriver.switchTo().window(newTab);
        Thread.sleep(5000);

        String page_title = webDriver.getTitle();
        assertEquals("Formula 1® 2023 Hospitality", page_title);

        WebElement header = webDriver.findElement(By.tagName("h1"));
        assertEquals("2025 Formula 1 Hospitality Calendar", header.getText());

        Actions action = new Actions(webDriver);
        WebElement swiperButton = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/button[1]"));
        action.moveToElement(swiperButton).perform();
        WebElement swiperSlide = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div"));
        assertTrue(swiperSlide.isDisplayed());
        Thread.sleep(500);

        swiperButton= webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/button[2]"));
        action.moveToElement(swiperButton).perform();
        Thread.sleep(1000);
        swiperSlide = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/div[3]/div/div/div/div"));
        assertTrue(swiperSlide.isDisplayed());

        swiperButton= webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[2]/div/button[3]"));
        action.moveToElement(swiperButton).perform();
        Thread.sleep(1000);
        swiperSlide = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/div[4]/div/div/div/div"));
        assertTrue(swiperSlide.isDisplayed());

        WebElement calendarItem;
        String backgroundColor;
        WebElement backgroundItem;
        for(int i=1; i<26; i++){
            calendarItem = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div["+i+"]/div/div"));
            action.moveToElement(calendarItem).perform();
            Thread.sleep(100);
            backgroundItem = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div["+i+"]/div/div/div[2]"));
            backgroundColor = backgroundItem.getCssValue("background-color");
            assertEquals("rgba(0, 0, 0, 1)", backgroundColor);
        }
    };

    @Order(2)
    @Test /* Test the F1 hospitality page links */
    void testHomepageLinks() throws InterruptedException {
        WebElement nav_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[4]/a"));
        nav_button.click();
        Set<String> windowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String originalTab = iterator.next();
        String newTab = iterator.next();
        webDriver.switchTo().window(newTab);
        webDriver.switchTo().window(originalTab);
        webDriver.close();
        webDriver.switchTo().window(newTab);
        Thread.sleep(5000);

        WebElement link;
        link = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/div[2]/div/div/div/div/a"));
        link.click();
        Thread.sleep(500);
        assertEquals("https://tickets.formula1.com/en/pcb-paddock-club-business", webDriver.getCurrentUrl());
        webDriver.navigate().back();
        Thread.sleep(500);

        WebElement calendarItem = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[1]/div/div"));
        Actions action = new Actions(webDriver);
        action.moveToElement(calendarItem).perform();
        Thread.sleep(100);
        link = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[3]/div/div[1]/div/div/div[2]/div/a[1]"));
        link.click();
        Thread.sleep(500);
        assertEquals("https://tickets.formula1.com/en/pc-57317-bahrain-preseason-paddock", webDriver.getCurrentUrl());
        webDriver.navigate().back();
        Thread.sleep(500);

        link=webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[4]/div/div/div/a[1]"));
        link.click();
        Thread.sleep(500);
        assertEquals("https://tickets.formula1.com/en", webDriver.getCurrentUrl());
        webDriver.navigate().back();
        Thread.sleep(1000);

        link=webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[4]/div/div/div/a[2]"));
        link.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);
        assertEquals("https://f1experiences.com/?utm_source=tickets.formula1.com&utm_medium=partner_referral&utm_campaign=f1tickets", webDriver.getCurrentUrl());







    };
}
