package org.agoncal.application.petstore.domain;

import org.agoncal.application.petstore.domain.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class OrderTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAnOrder() {

        // Creates an object
        Address address = new Address("Abbey road", "Liverpool", "SW17", "UK");
        Customer customer = new Customer("Paul", "Mc Cartney", "pmac", "pmac", "paul@beales.com", address);
        CreditCard creditCard = new CreditCard("123456789", CreditCardType.VISA, "12/45");
        Order order = new Order(customer, address, creditCard);

        // Persists the objecy
        tx.begin();
        em.persist(customer);
        em.persist(order);
        tx.commit();
        Long id = order.getId();

        // Finds the object by primary key
        order = em.find(Order.class, id);
        assertEquals(order.getCreditCardNumber(), "123456789");

        // Deletes the object
        tx.begin();
        em.remove(order);
        tx.commit();

        // Checks the object has been deleted
        assertNull("Should has been deleted", em.find(Order.class, id));
    }
}