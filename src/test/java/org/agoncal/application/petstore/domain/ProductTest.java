package org.agoncal.application.petstore.domain;

import org.agoncal.application.petstore.domain.AbstractDomainTest;
import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class ProductTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAProduct() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Bulldog", "Friendly dog from England", category);

        // Persists the objecy
        tx.begin();
        em.persist(category);
        em.persist(product);
        tx.commit();
        Long id = product.getId();

        // Finds the object by primary key
        product = em.find(Product.class, id);
        assertEquals(product.getName(), "Bulldog");

        // Updates the object
        tx.begin();
        product.setName("Big Bulldog");
        tx.commit();

        // Finds the object by primary key
        product = em.find(Product.class, id);
        assertEquals(product.getName(), "Big Bulldog");

        // Deletes the object
        tx.begin();
        em.remove(product);
        tx.commit();

        // Checks the object has been deleted
        assertNull("Should has been deleted", em.find(Product.class, id));
    }
}