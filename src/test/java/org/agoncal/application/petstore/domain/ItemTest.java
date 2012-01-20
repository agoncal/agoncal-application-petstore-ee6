package org.agoncal.application.petstore.domain;

import org.agoncal.application.petstore.domain.AbstractDomainTest;
import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Item;
import org.agoncal.application.petstore.domain.Product;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Antonio Goncalves
 */
public class ItemTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAnItem() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Bulldog", "Friendly dog from England", category);
        Item item = new Item("Thootless fish", 10f, "fish1.gif", product, "desc");

        // Persists the objecy
        tx.begin();
        em.persist(category);
        em.persist(product);
        em.persist(item);
        tx.commit();
        Long id = item.getId();

        // Finds the object by primary key
        item = em.find(Item.class, id);
        assertEquals(item.getName(), "Thootless fish");

        // Updates the object
        tx.begin();
        item.setName("Fish with big teeth");
        tx.commit();

        // Finds the object by primary key
        item = em.find(Item.class, id);
        assertEquals(item.getName(), "Fish with big teeth");

        // Deletes the object
        tx.begin();
        em.remove(item);
        tx.commit();

        // Checks the object has been deleted
        assertNull("Should has been deleted", em.find(Item.class, id));
    }
}