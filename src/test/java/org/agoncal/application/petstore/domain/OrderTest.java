package org.agoncal.application.petstore.domain;

import javax.inject.Inject;
import javax.validation.Validator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
@RunWith(Arquillian.class)
public class OrderTest {
    @Inject
    private Validator validator;

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Address.class, Customer.class, CreditCard.class, Order.class);
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidOrder() {

        // Creates an object
        Address address = new Address("Abbey road", "Liverpool", "SW17", "UK");
        Customer customer = new Customer("Paul", "Mc Cartney", "pmac", "pmac", "paul@beales.com", address);
        CreditCard creditCard = new CreditCard("123456789", CreditCardType.VISA, "12/45");
        Order order = new Order(customer, creditCard, address);

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(order).size());
    }
}