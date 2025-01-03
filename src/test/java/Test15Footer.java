import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

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
    void testFooterSponsorLinks(){};

    @Order(2)
    @Test
    void testFooterLinks(){};

    @Order(3)
    @Test
    void testFooterMarkup(){};
}
