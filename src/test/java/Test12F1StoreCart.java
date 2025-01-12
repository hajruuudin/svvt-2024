import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Iterator;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <b> <font color="white">TEST 12 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation, UI, Data Consistency </strong> <br/>
 * <strong> Testing the F1 Store Cart Function </strong> <br/>
 * This scenario test the F1 store cart and its functionalities. It should: <br/>
 *  - assert that the shopping cart is initially empty.<br/>
 *  - add a couple of items to the cart.<br/>
 *  - assert that the items we added really are the items in the cart.<br/>
 *  - delete the items from the cart.<br/>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
public class Test12F1StoreCart extends BaseTest {
    @Order(1)
    @Test /* Test the F1 shopping cart in the case we do not add anyhting */
    void testEmptyF1StoreCart() throws InterruptedException {
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        Set<String> windowHandles = webDriver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String originalTab = iterator.next();
        String newTab = iterator.next();
        webDriver.switchTo().window(newTab);
        webDriver.switchTo().window(originalTab);
        webDriver.close();
        webDriver.switchTo().window(newTab);
        Thread.sleep(5000);

        WebElement cart_button = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/div[1]/div[2]/a"));
        cart_button.click();
        Thread.sleep(5000);
        assertEquals("https://f1store.formula1.com/cart?_ref=cart-icon&qty=0", webDriver.getCurrentUrl());
        WebElement cart_text= webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div/div/div/div[1]"));
        assertEquals("Your Shopping Cart Is Empty", cart_text.getText());
    };

    @Order(2)
    @Test /* Test SHOULD test the F1 shopping cart in the case we add something in it */
    void testNotEmptyF1StoreCart() throws InterruptedException {
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        WebElement by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        WebElement team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[4]/a[1]"));
        team.click();
        Thread.sleep(500);
        WebElement driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[1]/div/nav/div[2]/div/div[1]/div[2]/ul/li[1]/a"));
        driver.click();
        Thread.sleep(500);
        WebElement max = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div[1]/div/a/img"));
        max.click();
        Thread.sleep(500);
        WebElement xlRadioButton = webDriver.findElement(By.xpath("//label[@class='radio size-selector-button available']//input[@value='XL']"));
        xlRadioButton.click();

        WebElement addToCart = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div[2]/div[11]/div/div/div[5]/div/div[2]/div/div[1]/div[1]/button"));
        addToCart.click();
        Thread.sleep(45000);
        webDriver.navigate().to("https://f1store.formula1.com/en/");
        Thread.sleep(500);
        WebElement cart_button = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/div[1]/div[2]/a"));
        cart_button.click();
        Thread.sleep(500);
        WebElement delete = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div/div[1]/div[1]/div[2]/div/div[3]/button"));
        delete.click();
        Thread.sleep(500);
    };
}
