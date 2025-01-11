import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <b> <font color="red">TEST 03 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store and its navigation </strong> <br/>
 * This test should cover the F1 store webpage, which is a sister site to the F1 Official webpage, the only
 * difference being the subdomain. Being one of the three main sites related to F1 (Home, Store, Tickets), it is
 * vital to test it. This test will navigate through the main navbar of the F1 store and the searching criteria,
 * ensuring it works as expected.
 * test
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test03F1StoreNavigation extends BaseTest {
    /* TO DO */
    @Order(1)
    @Test /* Test the navigation of the main navbar, as well as assert that the links work */
    void testMainNavbarFunctionality() throws InterruptedException {
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

        String page_title = webDriver.getTitle();
        assertEquals("F1 Store | The Official Formula One Store - Formula 1 Shop", page_title);

        WebElement nav_trackorder = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[1]/a"));
        nav_trackorder.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/track-order", webDriver.getCurrentUrl());

        WebElement nav_help = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[2]/a"));
        nav_help.click();
        Thread.sleep(5000);
        assertEquals("https://f1store.formula1.com/customer-help-desk", webDriver.getCurrentUrl());

        WebElement nav_account = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[3]/a"));
        nav_account.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/login?nextPathname=/account", webDriver.getCurrentUrl());

        WebElement nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        Thread.sleep(100);
        WebElement language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[1]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        Thread.sleep(100);
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[2]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=de-DE&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[3]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=es-ES&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[4]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=fr-FR&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[5]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=it-IT&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[6]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=ja-JP&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[7]/a"));
        language.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?loc=zh-CN&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_lang = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div"));
        nav_lang.click();
        language = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[4]/div/ul/li[1]/a"));
        language.click();

        WebElement nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        Thread.sleep(100);
        WebElement currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[1]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=AUD&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[2]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=CNY&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[3]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=EUR&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[4]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=GBP&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[5]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=JPY&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());
        nav_currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div"));
        nav_currency.click();
        currency = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/ul/li[5]/div/ul/li[6]/a"));
        currency.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/login?cur=USD&loc=en-GB&nextPathname=%2Faccount", webDriver.getCurrentUrl());

        WebElement nav_cart = webDriver.findElement(By.xpath("/html/body/div[2]/div/div/header/div[1]/div[2]/a"));
        nav_cart.click();
        Thread.sleep(100);
        assertEquals("https://f1store.formula1.com/cart?_ref=cart-icon&qty=0", webDriver.getCurrentUrl());


    };
    @Order(2)
    @Test /* Test the upper navbar, the one with the "My account" and "Currency", ensuring it works */
    void testSecondaryNavbar() throws InterruptedException {
        WebElement store_button = webDriver.findElement(By.xpath("/html/body/header/section[2]/nav/ul[2]/li[2]/a"));
        store_button.click();
        switchWindow(webDriver.getWindowHandle());
        Thread.sleep(5000);

        WebElement by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        WebElement team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[1]/a[1]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/alpine/t-21438535+z-9679180-2913012224?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[2]/a[1]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/haas-f1-team/t-21975490+z-9236924-3708324167?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[3]/a[1]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/mercedes-amg-petronas-f1-team/t-10977535+z-9539237-2587371245?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[4]/a[1]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/red-bull-racing/t-98080827+z-867377-1141549316?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[1]/a[2]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/aston-martin/t-32659625+z-9794350-777885981?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[2]/a[2]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/kick-sauber/t-10424252+z-9577029-3404546687?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[3]/a[2]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/pirelli/t-43094555+z-9599524-3555700904?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[4]/a[2]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/williams-racing/t-76089720+z-71011-3716927313?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[1]/a[3]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/scuderia-ferrari/t-76758670+z-71034-984427031?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[2]/a[3]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/mclaren-f1-team/t-21082084+z-9188766-2085991584?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[1]/div/div/div[3]/a[3]/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/rb/t-10421088+z-9325350-950311027?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/a"));
        by_team.click();
        Thread.sleep(500);
        team = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[1]/div/div[2]/div[2]/a/div[3]"));
        team.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/formula-1-merchandise/t-10764465+z-9628754-784327122?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement by_driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/a"));
        by_driver.click();
        Thread.sleep(500);
        WebElement driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/div/div/div[2]/div[2]/div/a[1]/div"));
        driver.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/ayrton-senna/a-4506339564+z-88131-2525293457?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/a"));
        by_driver.click();
        Thread.sleep(500);
        driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/div/div/div[2]/div[2]/div/a[2]/div"));
        driver.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/kimi-raikkonen/a-3473336185+z-972438-3664625995?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        by_driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/a"));
        by_driver.click();
        Thread.sleep(500);
        driver = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[2]/div/div/div[2]/div[2]/div/a[3]/div"));
        driver.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/michael-schumacher/a-9039115129+z-83122-3259681463?_ref=m-TOPNAV", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement men = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[3]/a"));
        men.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/men/ga-45+z-811776529-881130140", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement women = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[5]/a"));
        women.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/women/ga-13+z-9813284449-909104556", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement kids = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[6]/a"));
        kids.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/kids/ga-25+z-9847551775-2818815848", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement headwear = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[7]/a"));
        headwear.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/hats/d-19551120+z-9710312-1048507607", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement accessories = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[9]/a"));
        accessories.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/gifts-and-accessories/c-1270891598+z-998961-3472895370", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement collectibles = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[10]/a"));
        collectibles.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/collectibles-and-memorabilia/d-19442282+z-9051873-2107302736", webDriver.getCurrentUrl());
        webDriver.navigate().to("https://f1store.formula1.com/en/");

        WebElement clearance = webDriver.findElement(By.xpath("/html/body/div[2]/div/div[1]/header/nav/ul/li[11]/a"));
        clearance.click();
        Thread.sleep(500);
        assertEquals("https://f1store.formula1.com/en/sale-items/os-12+z-9170283383-2142475257", webDriver.getCurrentUrl());






    }
}
