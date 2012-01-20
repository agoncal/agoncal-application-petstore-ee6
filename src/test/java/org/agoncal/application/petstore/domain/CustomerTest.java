package org.agoncal.application.petstore.domain;

import org.agoncal.application.petstore.domain.AbstractDomainTest;
import org.agoncal.application.petstore.domain.Address;
import org.agoncal.application.petstore.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class CustomerTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateACustomer() {

        // Creates an object
        Address address = new Address("Abbey road", "Liverpool", "SW17", "UK");
        Customer customer = new Customer("Paul", "Mc Cartney", "pmac", "pmac", "paul@beales.com", address);

        // Persists the objecy
        tx.begin();
        em.persist(customer);
        tx.commit();
        Long id = customer.getId();

        // Finds the object by primary key
        customer = em.find(Customer.class, id);
        assertEquals(customer.getFirstname(), "Paul");

        // Updates the object
        tx.begin();
        customer.setFirstname("Ringo");
        tx.commit();

        // Finds the object by primary key
        customer = em.find(Customer.class, id);
        assertEquals(customer.getFirstname(), "Ringo");

        // Deletes the object
        tx.begin();
        em.remove(customer);
        tx.commit();

        // Checks the object has been deleted
        assertNull("Should has been deleted", em.find(Customer.class, id));
    }
}