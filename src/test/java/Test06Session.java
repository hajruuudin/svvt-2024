import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;


// NE RADI ZBOG STRANICE FIX LATER
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test06Session extends BaseTest{
    // CREDENTIALS
    // svvttest2024@gmail.com
    // TpHi123#

    @Order(1)
    @Test /* Pretty much necessary as we need to check if it is logged in */
    void testLogIn() throws InterruptedException {
        WebElement sign_in_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[1]"));
        sign_in_button.click();

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
        Thread.sleep(50000);
    }
}
