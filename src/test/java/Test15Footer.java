import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <b> <font color="white">TEST 15 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the footer and its functions  </strong> <br/>
 * This test should cover the footer on the Formula 1 homepage website and all of its endpoints. The
 * footer should have proper semantics and have links on it function and lead to the proper destinations.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test15Footer extends BaseTest{
    @Order(1)
    @Test
    void testFooterSocialLinks() throws InterruptedException {
        Actions action = new Actions(webDriver);
        String originalTab = webDriver.getWindowHandle();


        WebElement footer_social= webDriver.findElement(By.xpath("/html/body/footer/section[1]/div/ul[2]/li[1]/a"));
        action.moveToElement(footer_social).perform();
        Thread.sleep(500);
        footer_social.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(2000);
        assertEquals("https://www.facebook.com/Formula1", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(originalTab);
        Thread.sleep(500);

        footer_social= webDriver.findElement(By.xpath("/html/body/footer/section[1]/div/ul[2]/li[2]/a"));
        action.moveToElement(footer_social).perform();
        Thread.sleep(500);
        footer_social.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(2000);
        assertEquals("https://x.com/f1?mx=2", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(originalTab);
        Thread.sleep(500);

        footer_social= webDriver.findElement(By.xpath("/html/body/footer/section[1]/div/ul[2]/li[3]/a"));
        action.moveToElement(footer_social).perform();
        Thread.sleep(500);
        footer_social.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(2000);
        assertEquals("https://www.instagram.com/f1/", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(originalTab);
        Thread.sleep(500);

        footer_social= webDriver.findElement(By.xpath("/html/body/footer/section[1]/div/ul[2]/li[4]/a"));
        action.moveToElement(footer_social).perform();
        Thread.sleep(500);
        footer_social.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(2000);
        assertEquals("https://www.youtube.com/F1", webDriver.getCurrentUrl());
        webDriver.close();
        webDriver.switchTo().window(originalTab);
        Thread.sleep(500);

    };

    @Order(2)
    @Test
    void testFooterLinks()  throws InterruptedException {
        Actions action = new Actions(webDriver);
        WebElement link;

        int[] numbers = {1, 2, 3, 4, 5, 6};
        for(int i : numbers){
            WebElement footer = webDriver.findElement(By.xpath("/html/body/footer"));
            action.moveToElement(footer).perform();
            Thread.sleep(500);
            link=webDriver.findElement(By.xpath("/html/body/footer/section[2]/div[3]/div[1]/ul/li["+i+"]/a"));
            link.click();
            Thread.sleep(500);
            switch (i){
                case 1:
                    assertEquals("https://www.formula1.com/en/latest", webDriver.getCurrentUrl());
                    break;
                case 2:
                    assertEquals("https://www.formula1.com/en/page/what-is-f1", webDriver.getCurrentUrl());
                    break;
                case 3:
                    assertEquals("https://www.formula1.com/en/video", webDriver.getCurrentUrl());
                    break;
                case 4:
                    assertEquals("https://www.formula1.com/en/drivers", webDriver.getCurrentUrl());
                    break;
                case 5:
                    assertEquals("https://www.formula1.com/en/teams", webDriver.getCurrentUrl());
                    break;
                case 6:
                    assertEquals("https://www.formula1.com/en/racing/2025", webDriver.getCurrentUrl());
                    break;
            }
        }
        int[] numbers2 = {2, 3};

        for(int i : numbers2){
            WebElement footer = webDriver.findElement(By.xpath("/html/body/footer"));
            action.moveToElement(footer).perform();
            Thread.sleep(500);
            link=webDriver.findElement(By.xpath("/html/body/footer/section[2]/div[3]/div["+i+"]/a"));
            link.click();
            Thread.sleep(500);
            switch (i){
                case 2:
                    assertEquals("https://www.formula1.com/en/results/2024/races", webDriver.getCurrentUrl());
                    break;
                case 3:
                    assertEquals("https://www.formula1.com/en/page/gaming", webDriver.getCurrentUrl());
                    break;
            }
        }


    };
}
