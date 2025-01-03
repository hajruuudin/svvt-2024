import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * <b> <font color="white">TEST 12 - EXTRA</font> </b> <br/>
 * <strong/> Category: Navigation, UI, Data Consistency </strong> <br/>
 * <strong> Testing the F1 Store Cart Function </strong> <br/>
 * This scenario test the F1 store cart and its functionalities. It should: <br/>
 *  - assert that the shopping cart is initially empty.<br/>
 *  - add a couple of items to the cart.<br/>
 *  - assert that the items we added really are the items in the cart.<br/>
 *  - delete the items from the cart.<br/>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test12F1StoreCart {
    @Order(1)
    @Test
    void testEmptyF1StoreCart(){};

    @Order(2)
    @Test
    void testNotEmptyF1StoreCart(){};
}
