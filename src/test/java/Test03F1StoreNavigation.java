import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * <b> <font color="red">TEST 03 - CRITICAL</font> </b> <br/>
 * <strong/> Category: Navigation & UI </strong> <br/>
 * <strong> Testing the F1 Store and its navigation </strong> <br/>
 * This test should cover the F1 store webpage, which is a sister site to the F1 Official webpage, the only
 * difference being the subdomain. Being one of the three main sites related to F1 (Home, Store, Tickets), it is
 * vital to test it. This test will navigate through the main navbar of the F1 store and the searching criteria,
 * ensuring it works as expected.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test03F1StoreNavigation {
    /* TO DO */
    @Order(1)
    @Test /* Test the navigation of the main navbar, as well as assert that the links work */
    void testMainNavbarFunctionality(){};

    @Order(2)
    @Test /* Test the sidebar when shopping, ensuring that the filters and selects are clickable and update the UI */
    void testShoppingSidebar(){}

    @Order(3)
    @Test /* Test the upper navbar, the one with the "My account" and "Currency", ensuring it works */
    void testSecondaryNavbar(){}
}
