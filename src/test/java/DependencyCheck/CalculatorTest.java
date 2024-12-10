package DependencyCheck;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

// Class made just to test all the functionalities of JUnit
class CalculatorTest {
    private static Calculator calc;
    private static WebDriver driver;
    private static String baseUrl;

    @BeforeAll
    static void setUp(){
        calc = new Calculator();
        // SetUp for Hajrudin Browser
        System.setProperty("webdriver.gecko.driver", "/Users/hajrudin.imamovic/Documents/Drivers/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        driver = new FirefoxDriver(options);
        baseUrl = "https://www.formula1.com/";
        // SetUp for Tarik Browser
        /* Ovde helem moras ti svoj setup odreadit */
    }

    @Test
    void testPositiveCalculateSum(){
        assertEquals(6, calc.calcualteSum(3,3));
        assertEquals(10, calc.calcualteSum(2,8));
        assertEquals(1, calc.calcualteSum(1,0));
    }

    @ParameterizedTest
    @CsvSource(value = {"1:2:3", "3:4:7", "10:12:22"}, delimiter = ':')
    void testCalculateSumParametrized(int a, int b, int sum){
        assertEquals(sum, calc.calcualteSum(a,b));
    }

    @Test
    void testSeleniumDriver(){
        driver.get(baseUrl);
        String path = driver.getCurrentUrl();
        assertEquals(baseUrl, path);
    }

    @AfterAll
    static void tearDown(){
        System.out.println("This will run after all tests");
        if(driver != null){
            driver.close();
        }
    }


}