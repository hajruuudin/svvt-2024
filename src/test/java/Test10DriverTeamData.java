import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <b> <font color="red">TEST 10 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the driver & team pages </strong> <br/>
 * This scenario pretty much goes over the Driver Data & Team Data pages, going mainly over its data
 * and consistency, along with the UI. It should assert that the data is set up for
 * proper navigation and that the data is accurate on all races.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test10DriverTeamData extends BaseTest{
    @Order(1)
    @Test /* This test goes through all the drivers and their data */
    void testDriverPage() throws InterruptedException {
        Random rand = new Random();
        WebElement nav_button = webDriver.findElement(By.id("Drivers"));
        actions.moveToElement(nav_button).perform();
        Thread.sleep(1000);
        nav_button.click();
        Thread.sleep(1000);

        WebElement drivers_container = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div/div[4]"));
        List<WebElement> driver_articles = drivers_container.findElements(By.tagName("a"));
        for(WebElement driver : driver_articles){
            styleTesting(
                    driver.findElement(By.tagName("div")),
                    "margin",
                    false
            );
        }

        for(int i = 0; i < 10; i++){
            drivers_container = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div/div[4]"));
            driver_articles = drivers_container.findElements(By.tagName("a"));
            int num = rand.nextInt(driver_articles.size());
            WebElement article = driver_articles.get(num);
            String driver_number = article.findElement(By.xpath(".//div/div/div[3]/img[1]")).getAttribute("alt");
            String driver_name = article.findElement(By.xpath(".//div/div/div[2]/div/div/p[1]")).getText();
            String driver_surname = article.findElement(By.xpath(".//div/div/div[2]/div/div/p[2]")).getText();
            String driver_country = article.findElement(By.xpath(".//div/div/div[2]/img")).getAttribute("alt");
            article.click();

            Thread.sleep(1000);
            String target_name = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/figure/figcaption/div/h1")).getText();
            String target_country = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/figure/figcaption/div/div/p/img")).getAttribute("alt");
            String target_number = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/figure/figcaption/div/div/p")).getText();

            assertEquals(target_name.toUpperCase(), driver_name + " " + driver_surname,
                    "Name should be consistent: " + target_name + " - " + driver_name + driver_surname + "!");
            assertTrue(target_number.contains(driver_number.substring(21)),
                    "Number should be the same!");
            assertEquals(target_country, driver_country,
                    "Country must be the same!");
            webDriver.navigate().to("https://www.formula1.com/en/drivers");
            Thread.sleep(1000);
        }
    }

    @Order(2)
    @Test /* This test goes through all the constructors and their data */
    void testTeamPage() throws InterruptedException {
        Random rand = new Random();
        WebElement nav_button = webDriver.findElement(By.id("Teams"));
        actions.moveToElement(nav_button).perform();
        Thread.sleep(1000);
        nav_button.click();
        Thread.sleep(1000);

        WebElement teams_container = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div/div[4]"));
        List<WebElement> teams_articles = teams_container.findElements(By.tagName("a"));
        for(WebElement team : teams_articles){
            styleTesting(
                    team.findElement(By.tagName("div")),
                    "margin",
                    false
            );
        }

        for(int i = 0; i < 5; i++){
            teams_container = webDriver.findElement(By.xpath("/html/body/main/div[1]/div/div/div[4]"));
            teams_articles = teams_container.findElements(By.tagName("a"));
            int num = rand.nextInt(teams_articles.size()-1);
            WebElement article = teams_articles.get(num);
            String team_name = article.findElement(By.xpath(".//div/div/div[2]/div/span")).getText();
            String team_driver_01_name = article.findElement(By.xpath(".//div/div/div[3]/div[1]/div/div/p[1]")).getText();
            String team_driver_01_lastname = article.findElement(By.xpath(".//div/div/div[3]/div[1]/div/div/p[2]")).getText();
            String team_driver_01 = team_driver_01_name + " " + team_driver_01_lastname;
            String team_driver_02_name = article.findElement(By.xpath(".//div/div/div[3]/div[2]/div/div/p[1]")).getText();
            String team_driver_02_lastname = article.findElement(By.xpath(".//div/div/div[3]/div[2]/div/div/p[2]")).getText();
            String team_driver_02 = team_driver_02_name + " " + team_driver_02_lastname;
            article.click();

            Thread.sleep(1000);
            String target_name = webDriver.findElement(By.xpath("/html/body/main/div/div/h1")).getText();
            String target_driver_01 = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/div[2]/figure[1]/a/figcaption/div/p[2]")).getText();
            String target_driver_02 = webDriver.findElement(By.xpath("/html/body/main/div/div/div/div[1]/div[2]/figure[2]/a/figcaption/div/p[2]")).getText();

            System.out.println(target_driver_01);
            System.out.println(team_driver_01);

            assertEquals(target_name, team_name,
                    "Names must be consistent: " + target_name + " - " + team_name);
            assertEquals(target_driver_01.toUpperCase(), team_driver_01.toUpperCase(),
                    "Driver 01 must be consistent: " + target_driver_01.toUpperCase() + " - " + team_driver_01.toUpperCase());
            assertEquals(target_driver_02.toUpperCase(), team_driver_02.toUpperCase(),
                    "Driver 02 must be consistent: " + target_driver_02.toUpperCase() + " - " + team_driver_02.toUpperCase());
            webDriver.navigate().to("https://www.formula1.com/en/teams");
            Thread.sleep(1000);
        }
    }
}
