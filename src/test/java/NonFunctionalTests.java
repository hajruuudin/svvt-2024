import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

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
@SuppressWarnings("all")
public class NonFunctionalTests extends BaseTest {
    private static final String TITLE = "Mr";
    private static final String FIRSTNAME = "Mujo";
    private static final String LASTNAME = "Mujic";
    private static final String COUNTRY = "BIH";
    private static final String DATE = "05/03/2003";
    private static final String EMAIL = "svvttest2024@gmail.com";
    private static final String PASS = "TpHi123#";

    @Order(1)
    @Test /* This test SHOULD assert that the user is logged in and check that the user is logged in via UI changes */
    void testLogIn() throws InterruptedException, IOException {
        Thread.sleep(1000);
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
    @Test /* Test SHOULD test the register form and its error messages */
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

        WebElement register_button = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form/div[10]/div[1]/button"));
        scrollToElement(register_button, 0);
        register_button.click();
        Thread.sleep(2000);

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

    @Order(3)
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

    @Order(4)
    @Test /* Test SHOULD check the checkout form with proper input */
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
//        WebElement xlRadioButton = webDriver.findElement(By.xpath("//label[@class='radio size-selector-button available']//input[@value='XL']"));
//        xlRadioButton.click();
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

    @Order(5)
    @Test /* Test SHOULD check the checkout form with improper input */
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
//        WebElement xlRadioButton = webDriver.findElement(By.xpath("//label[@class='radio size-selector-button available']//input[@value='XL']"));
//        xlRadioButton.click();
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
