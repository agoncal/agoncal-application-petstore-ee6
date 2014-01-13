package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.*;
import org.agoncal.application.petstore.exception.ValidationException;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@RunWith(Arquillian.class)
public class OrderServiceIT extends AbstractServiceIT {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private OrderService orderService;
    @Inject
    private CustomerService customerService;

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCRUDanOrder() {

        // Finds all the objects
        int initialNumber = orderService.findAllOrders().size();

        // Creates an object
        Address address = new Address("78 Gnu Rd", "Texas", "666", "WWW");
        Customer customer = new Customer("Richard", "Stallman", "rich", "rich", "rich@gnu.org", address);
        CreditCard creditCard = new CreditCard("1234", CreditCardType.MASTER_CARD, "10/12");

        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Angelfish", "Saltwater fish from Australia", category);
        Item item = new Item("Large", 10.00f, "fish1.jpg", product, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum velit ante, malesuada porta condimentum eget, tristique id magna. Donec ac justo velit. Suspendisse potenti. Donec vulputate vulputate molestie. Quisque vitae arcu massa, dictum sodales leo. Sed feugiat elit vitae ante auctor ultrices. Duis auctor consectetur arcu id faucibus. Curabitur gravida.");

        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(new CartItem(item, 2));

        // Persists the object
        customer = customerService.createCustomer(customer);
        Order order = orderService.createOrder(customer, creditCard, cartItems);
        Long id = order.getId();

        // Finds all the objects and checks there's an extra one
        assertEquals("Should have an extra object", initialNumber + 1, orderService.findAllOrders().size());

        // Finds the object by id
        order = orderService.findOrder(id);
        assertNotNull(order.getOrderDate());

        // Deletes the object
        orderService.removeOrder(order);

        // Checks the object has been deleted
        assertNull("Should has been deleted", orderService.findOrder(id));

        // Finds all the objects and checks there's one less
        assertEquals("Should have an extra object", initialNumber, orderService.findAllOrders().size());
    }

    @Test(expected = ValidationException.class)
    public void shouldNotCreateAnOrderWithAnEmptyCart() {

        // Creates an object
        Address address = new Address("78 Gnu Rd", "Texas", "666", "WWW");
        Customer customer = new Customer("Richard", "Stallman", "rich", "rich", "rich@gnu.org", address);
        CreditCard creditCard = new CreditCard("1234", CreditCardType.MASTER_CARD, "10/12");
        List<CartItem> cartItems = new ArrayList<CartItem>();

        // Persists the object
        orderService.createOrder(customer, creditCard, cartItems);
    }
}
