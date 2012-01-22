package org.agoncal.application.petstore.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
public class ProductTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidProduct() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Bulldog", "Friendly dog from England", category);

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(product).size());
    }
}