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
public class AddressTest {
    @Inject
    private Validator validator;

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Address.class);
    }

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