import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="white">TEST 11 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store Search Function </strong> <br/>
 * This scenario test the F1 store search bar and tests if the search criteria matches the showed results.
 * It also tests to ensure that any random string will not find any results.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test11F1StoreSearch extends BaseTest {
    @Order(1)
    @Test /* Test to see if a proper search will lead to normal results */
    void testPositiveSearch() throws InterruptedException {
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        WebElement search_bar = webDriver.findElement(By.xpath("//*[@id=\"typeahead-input-desktop\"]"));
        search_bar.sendKeys("max");
        WebElement search_button  = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/div[2]/div[2]/div/button"));
        search_button.click();
        Thread.sleep(3000);

        WebElement items_container = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[2]/div[2]/div/div[2]"));
        List<WebElement> items = items_container.findElements(By.xpath("./div"));
        Random rand = new Random();
        Thread.sleep(1000);
        boolean match = false;

        for(int i = 0; i < 10; i++){
            String item_name = items.get(rand.nextInt(items.size())).findElement(By.xpath(".//div/div[2]/div[2]/a")).getText();
            if(item_name.toLowerCase().contains("max") || item_name.toLowerCase().contains("champion")){
                match = true;
            }

            assertTrue(match, "The item name should contain the keyword: " + item_name.toLowerCase() + " - " + "max or champion");
        }
    }

    @Order(2)
    @Test /* Test to see if an invalid search will lead to no results at all */
    void testNegativeSearch() throws InterruptedException {
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        WebElement search_bar = webDriver.findElement(By.xpath("//*[@id=\"typeahead-input-desktop\"]"));
        search_bar.sendKeys("ndsfjkhgfjofs");
        WebElement search_button  = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/div[2]/div[2]/div/button"));
        search_button.click();
        Thread.sleep(3000);

        WebElement notfound = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[2]/div[2]/div/div/p"));
        assertEquals("Sorry, your search for ndsfjkhgfjofs did not match any products.", notfound.getText());

    }
}
