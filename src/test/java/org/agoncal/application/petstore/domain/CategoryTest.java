package org.agoncal.application.petstore.domain;

import org.agoncal.application.petstore.domain.AbstractDomainTest;
import org.agoncal.application.petstore.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class CategoryTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateACategory() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");

        // Persists the objecy
        tx.begin();
        em.persist(category);
        tx.commit();
        Long id = category.getId();

        // Finds the object by primary key
        category = em.find(Category.class, id);
        assertEquals(category.getName(), "Fish");

        // Updates the object
        tx.begin();
        category.setName("Big Fish");
        tx.commit();

        // Finds the object by primary key
        category = em.find(Category.class, id);
        assertEquals(category.getName(), "Big Fish");

        // Deletes the object
        tx.begin();
        em.remove(category);
        tx.commit();

        // Checks the object has been deleted
        assertNull("Should has been deleted", em.find(Category.class, id));
    }
}