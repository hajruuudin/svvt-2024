import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * <b> <font color="white">TEST 14 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store Homepage </strong> <br/>
 * This test should cover the footer on the F1 Store Homepage, ensuring that all the markup is as expected
 * all the links work and the styling is appropriate.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test14F1StoreHomepage {
    @Order(1)
    @Test
    void testHomepageMarkup(){};

    @Order(2)
    @Test
    void testHomepageLinks(){};
}
