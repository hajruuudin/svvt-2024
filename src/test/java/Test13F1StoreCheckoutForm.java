import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <b> <font color="white">TEST 13 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store Checkout Form </strong> <br/>
 * This test should cover the footer on the F1 Cart checkout form, ensuring that, once an item has been added,
 * we can proceed to checkout and fill in the information as desired.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test13F1StoreCheckoutForm extends BaseTest{
    @Order(1)
    @Test
    void testCheckoutFormSuccess() throws InterruptedException {
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
        Thread.sleep(7000);
        WebElement by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        WebElement team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[4]/a[1]"));
        team.click();
        Thread.sleep(5000);
        WebElement driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[1]/div/nav/div[2]/div/div[1]/div[2]/ul/li[1]/a"));
        driver.click();
        Thread.sleep(500);
        WebElement max = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/div[1]/div/a/img"));
        max.click();
        Thread.sleep(500);
        WebElement xlRadioButton = webDriver.findElement(By.xpath("//label[@class='radio size-selector-button available']//input[@value='XL']"));
        xlRadioButton.click();
        Thread.sleep(500);
        WebElement addToCart = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div[2]/div[11]/div/div/div[5]/div/div[2]/div/div[1]/div[1]/button"));
        addToCart.click();
        Thread.sleep(40000);
        WebElement checkoutButton = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div/div[1]/div[2]/div[1]/div[3]/div[3]/button"));
        checkoutButton.click();
        Thread.sleep(2000);
        WebElement name = webDriver.findElement(By.name("firstName"));
        name.clear();
        name.sendKeys("Tarik");
        Thread.sleep(200);
        WebElement lastName = webDriver.findElement(By.name("lastName"));
        lastName.clear();
        lastName.sendKeys("Perviz");
        Thread.sleep(200);
        WebElement email = webDriver.findElement(By.name("email"));
        email.clear();
        email.sendKeys("tarik.perviz@stu.ibu.edu.ba");
        Thread.sleep(200);
        WebElement phone = webDriver.findElement(By.name("phone"));
        phone.clear();
        phone.sendKeys("061234567");
        Thread.sleep(200);
        WebElement address = webDriver.findElement(By.name("addressLine1"));
        address.clear();
        address.sendKeys("Humacka ploca 1");
        Thread.sleep(200);
        WebElement city = webDriver.findElement(By.name("city"));
        city.clear();
        city.sendKeys("London");
        Thread.sleep(200);
        WebElement zip = webDriver.findElement(By.name("postalCode"));
        zip.clear();
        zip.sendKeys("SW1P 3PA");
        Thread.sleep(200);
        WebElement state = webDriver.findElement(By.name("state"));
        state.clear();
        state.sendKeys("Federacija BH");
        Thread.sleep(200);
        WebElement checkout_button = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div/form/div[3]/button"));
        checkout_button.click();
        Thread.sleep(5000);
        assertEquals("https://f1store.formula1.com/payment", webDriver.getCurrentUrl());
    };
    @Order(2)
    @Test
    void testCheckoutFormFailure() throws InterruptedException{
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(7000);
        WebElement by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        WebElement team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[4]/a[1]"));
        team.click();
        Thread.sleep(500);
        WebElement driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[1]/div/nav/div[2]/div/div[1]/div[2]/ul/li[1]/a"));
        driver.click();
        Thread.sleep(500);
        WebElement max = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div[2]/div[2]/div/div[2]/div[2]/div/div[1]/div[1]/div/a/img"));
        max.click();
        Thread.sleep(500);
        WebElement xlRadioButton = webDriver.findElement(By.xpath("//label[@class='radio size-selector-button available']//input[@value='XL']"));
        xlRadioButton.click();
        Thread.sleep(500);
        WebElement addToCart = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div[2]/div[11]/div/div/div[5]/div/div[2]/div/div[1]/div[1]/button"));
        addToCart.click();
        Thread.sleep(5000);
        WebElement checkoutButton = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[7]/div/div[1]/div[2]/div[1]/div[3]/div[3]/button"));
        checkoutButton.click();
        Thread.sleep(2000);
        WebElement edit = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div[1]/div[1]/div/div/div/div/div[1]/div/div[2]/div[2]/a"));
        edit.click();
        Thread.sleep(500);
        WebElement name = webDriver.findElement(By.name("firstName"));
        name.clear();
        Thread.sleep(500);
        WebElement error = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[6]/div/form/div[1]/div[2]/div[1]/div[1]/div"));
        assertTrue(error.isDisplayed());
        Thread.sleep(1000);


    }
}
