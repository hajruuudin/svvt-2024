import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 06 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & Authorisation </strong> <br/>
 * <strong> Testing Authorisation on the Webpage</strong> <br/>
 * This scenario should cover the session handling inside the web application, ensuring that
 * we cannot access any functions of the application without being previously logged in.
 * This test serves as one of the two replacement tests for the Session tests, related to logging in and singing up.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SuppressWarnings("all")
public class Test06SecurityConventions extends BaseTest{
    @Order(1)
    @Test /* Test the F1 subscribe page and that all of its buttons will not allow subscribing without logging in */
    void testAuthorisation() throws InterruptedException {
        WebElement subscribe_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[2]"));
        subscribe_button.click();
        Thread.sleep(2000);

        authorisationCheck(webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[1]/div/div/a")));
        authorisationCheck(webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[2]/div[2]/div[2]/div[1]/div/div[2]/div/div/a")));
        authorisationCheck(webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[7]/div[2]/div[2]/div/div[1]/div/a")));
        authorisationCheck(webDriver.findElement(By.xpath("/html/body/div[1]/div/main/section/section[7]/div[2]/div[2]/div/div[2]/div/a")));
    }

    @Order(2)
    @Test /* Test to see if the HTTP2 protocol is implemented on all F1 related main pages */
    void testHttpsProtocol() throws InterruptedException {
        String currentUrl = webDriver.getCurrentUrl();
        assert currentUrl != null;
        assertTrue(currentUrl.startsWith("https://"), "The HTTP2.0 Protocol should be implemented across all F1 related sites!");

        webDriver.navigate().to("https://www.f1authentics.com/");
        Thread.sleep(1000);
        currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "The HTTP2.0 Protocol should be implemented across all F1 related sites!");

        webDriver.navigate().to("https://f1store.formula1.com/en/?_s=bm-fi-f1-prtsite-topnav-230720-jm");
        Thread.sleep(1000);
        currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "The HTTP2.0 Protocol should be implemented across all F1 related sites!");

        webDriver.navigate().to("https://tickets.formula1.com/en");
        Thread.sleep(1000);
        currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "The HTTP2.0 Protocol should be implemented across all F1 related sites!");
    }
}
