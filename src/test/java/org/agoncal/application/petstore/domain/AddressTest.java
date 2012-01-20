package org.agoncal.application.petstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
public class AddressTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidAddress() {

        // Creates an object
        Address address = new Address("Street1", "City", "Zipcode", "Country");

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(address).size());
    }
}