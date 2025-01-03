import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * <b> <font color="white">TEST 13 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store Checkout Form </strong> <br/>
 * This test should cover the footer on the F1 Cart checkout form, ensuring that, once an item has been added,
 * we can proceed to checkout and fill in the information as desired.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test13F1StoreCheckoutForm {
    @Order(1)
    @Test
    void testOrderFull(){};
}
