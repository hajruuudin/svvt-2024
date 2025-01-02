import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Test08F1TicketsPage extends BaseTest{
    @Order(1)
    @Test
    void testF1TicketsMarkup() throws InterruptedException {
        WebElement tickets_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[3]/a"));
        tickets_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        String page_title = webDriver.getTitle();
        assertEquals("F1® Ticket Store | Formula1.com", page_title);

        WebElement header = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[3]/div/div/div[1]/div"));
        assertEquals("F1® TICKETS RACE CALENDAR", header.getText());

        WebElement title = webDriver.findElement(By.tagName("h1"));
        assertEquals("2025 FIA FORMULA ONE WORLD CHAMPIONSHIP", title.getText());
    }

    @Order(2)
    @Test
    void testF1TicketsStyling() throws InterruptedException {
        WebElement tickets_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[3]/a"));
        tickets_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        WebElement races_container = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div"));
        List<WebElement> races = races_container.findElements(By.xpath("./div"));
        scrollToElement(races.get(1), -200);
        for(WebElement r : races){
            styleTesting(
                    r.findElement(By.xpath(".//div/div/div[2]/p")),
                    "color",
                    false
            );
        }
    }
}
