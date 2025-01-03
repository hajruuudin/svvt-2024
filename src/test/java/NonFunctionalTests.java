import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <strong><font color="white">NON FUNCTIONAL TEST CASES</font></strong>
 * <br/>
 * Due to site bot protection and anti web-scraping techniques implemented by the Formula 1 website, we weren't
 * able to test some areas of the webpage. Specifically, the areas we weren't able to test are the <strong>Login and Registering</strong>
 * aspects of the webpage itself. We presume that the submission of the Login and Register forms works normally, since
 * we can see that a request was made, however, it is immediately denied by the web server indicating a CORS error
 * (although we believe this to be just a default error sent back as CORS is not an issue on the other website pages).
 * <br/><br/>
 * Because of this, we decided to skip testing the full Login and Register functions with actual proof that we have been
 * logged in / registered and decided to only test the client side navigation and form navigation / input. This
 * is about as much as we can test related to the authentication. We also tested that we are not allowed to subscribe
 * to any account specific functions on the website without an account by asserting that we are redirected to the login page.
 * <br/><br/>
 * Below we have left an implementation of how we would go about testing the register and login processes individually
 * as two separate test cases. They will not work due to the site-protection but leave an idea as to how we would
 * go about testing it. The cases include a larger amount of comments for better explanations.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NonFunctionalTests extends BaseTest {
    @Order(1)
    @Test /* This test SHOULD assert that the user is logged in and check that the user is logged in via UI changes */
    void testLogIn() throws InterruptedException, IOException {
        Thread.sleep(1000000);
        WebElement sign_in_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[1]"));
        actions.moveToElement(sign_in_button).perform();
        actions.click().perform();


        Thread.sleep(3000);
        assertEquals(
                "https://account.formula1.com/#/en/login?redirect=https%3A%2F%2Fwww.formula1.com%2F&lead_source=web_f1core",
                webDriver.getCurrentUrl(),
                "The URL should be redirected to the Log In Page"
        );

        WebElement email_input = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[2]/input"));
        WebElement password_input = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[3]/input"));
        WebElement sign_in = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[4]/button"));

        logIn(
                email_input,
                password_input,
                sign_in,
                "svvttest2024@gmail.com",
                "TpHi123#"
        );
        Thread.sleep(1000);
    }

    @Order(2)
    @Test
    void testRegister(){
        // TODO - registracija template
    }
}
