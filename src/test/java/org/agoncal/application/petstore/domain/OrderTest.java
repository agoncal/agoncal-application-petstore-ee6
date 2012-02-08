package org.agoncal.application.petstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
public class OrderTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidOrder() {

        // Creates an object
        Address address = new Address("Abbey road", "Liverpool", "SW17", "UK");
        Customer customer = new Customer("Paul", "Mc Cartney", "pmac", "pmac", "paul@beales.com", address);
        CreditCard creditCard = new CreditCard("123456789", CreditCardType.VISA, "12/45");
        Order order = new Order(customer, creditCard);

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(order).size());
    }
}