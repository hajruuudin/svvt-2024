import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 05 - CRITICAL - REPLACEMENT</font> </b> <br/>
 * <strong/> Category: Navigation, Verification & UI </strong> <br/>
 * <strong> Testing User sign up form and login functionality & UI</strong> <br/>
 * This scenario should cover the markup of the signup and login form. It should cover the
 * inputs, selects (if any), buttons and so on... This test serves as one of the two replacement tests for the
 * Session tests, related to logging in and singing up.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test05AuthenticationForms extends BaseTest {
    private static final String TITLE = "Mr";
    private static final String FIRSTNAME = "Mujo";
    private static final String LASTNAME = "Mujic";
    private static final String COUNTRY = "BIH";
    private static final String DATE = "05/03/2003";
    private static final String EMAIL = "svvttest2024@gmail.com";
    private static final String PASS = "TpHi123#";

    @Order(1)
    @Test /* FOR SOME REASON THIS DOES NOT WORK! Test the register form and its error messages */
    void testSignUpForm() throws InterruptedException {
        WebElement sign_in_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[1]"));
        sign_in_button.click();
        Thread.sleep(2000);

        assertEquals("https://account.formula1.com/#/en/login?redirect=https%3A%2F%2Fwww.formula1.com%2F&lead_source=web_f1core", webDriver.getCurrentUrl());

        WebElement nav_links = webDriver.findElement(By.xpath("/html/body/div[1]/header/div/div[2]/ul"));
        nav_links.findElement(By.xpath(".//li[2]")).click();
        Thread.sleep(1000);

        assertEquals("https://account.formula1.com/#/en/register?redirect=https%3A%2F%2Fwww.formula1.com%2F&lead_source=web_f1core", webDriver.getCurrentUrl());
        Thread.sleep(1000);

        WebElement e_firstName = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/div[2]/p"));
        WebElement e_lastName = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[3]/div[2]/p"));
        WebElement e_date = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[4]/div[2]/p"));
        WebElement e_cor = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[5]/div[2]/p"));
        WebElement e_email = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[6]/div[2]/p"));

        assertEquals("First Name is required", e_firstName.getText());
        assertEquals("Last Name is required", e_lastName.getText());
        assertEquals("Birth Date is required", e_date.getText());
        assertEquals("Country of Residence is required", e_cor.getText());
        assertEquals("Email Address is required", e_email.getText());

        Select title_select = new Select(webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[1]/div/div/select")));
        scrollToElement((WebElement) title_select, -200);
        title_select.selectByValue(TITLE);
        Thread.sleep(300);

        WebElement firstName_input = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[2]/div[1]/div/input"));
        scrollToElement(firstName_input, -200);
        firstName_input.sendKeys(FIRSTNAME);
        Thread.sleep(300);

        WebElement lastName_input = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[3]/div[1]/div/input"));
        scrollToElement(lastName_input, -200);
        firstName_input.sendKeys(LASTNAME);
        Thread.sleep(300);

        WebElement dateOfBirth_input = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[4]/div[1]/div/input"));
        scrollToElement(dateOfBirth_input, -200);
        firstName_input.sendKeys(DATE);
        Thread.sleep(300);

        Select country_select = new Select(webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[5]/div[1]/div/select")));
        scrollToElement((WebElement) country_select, -200);
        country_select.selectByValue(COUNTRY);
        Thread.sleep(300);

        WebElement email_input = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[6]/div[1]/div/input"));
        scrollToElement(email_input, -200);
        email_input.sendKeys(EMAIL);
        Thread.sleep(300);

        WebElement password_input = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[7]/div[1]/div/input"));
        scrollToElement(password_input, -200);
        password_input.sendKeys(PASS);
        Thread.sleep(300);
    }

    @Order(2)
    @Test /* Test the sign-in form and its error messages */
    void testSignInForm() throws InterruptedException {
        WebElement sign_in_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/div/a[1]"));
        actions.moveToElement(sign_in_button).perform();
        actions.click().perform();

        Thread.sleep(3000);
        assertEquals(
                "https://account.formula1.com/#/en/login?redirect=https%3A%2F%2Fwww.formula1.com%2F&lead_source=web_f1core",
                webDriver.getCurrentUrl(),
                "The URL should be redirected to the Log In Page"
        );

        WebElement login_button = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[4]/button"));
        login_button.click();

        WebElement e_email = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[2]/span"));
        WebElement e_pass = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[3]/span[1]"));

        assertEquals("Please enter valid username/email address", e_email.getText());
        assertEquals("Password is not correct", e_pass.getText());

        Thread.sleep(1000);
        WebElement email_input = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[2]/input"));
        WebElement password_input = webDriver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/form/div[3]/input"));

        logIn(
                email_input,
                password_input,
                login_button,
                "svvttest2024@gmail.com",
                "TpHi123#"
        );

        Thread.sleep(500);

        WebElement error = webDriver.findElement(By.xpath("//*[@id=\"loginform\"]/div[1]/span"));
        assertEquals("Sorry something went wrong", error.getText());

        Thread.sleep(1000);
    }

}
