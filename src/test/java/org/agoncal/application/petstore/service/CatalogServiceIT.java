package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.Address;
import org.agoncal.application.petstore.domain.Category;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.validation.ConstraintViolationException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
//@Ignore
@RunWith(Arquillian.class)
public class CatalogServiceIT extends AbstractServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CatalogService catalogService;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Address.class.getPackage())
                .addPackage(CatalogService.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
        return archive;
    }

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateACategory() {

        // Finds all the objects
        int initialNumber = catalogService.findAllCategories().size();

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");

        // Persists the object
        category = catalogService.createCategory(category);
        Long id = category.getId();

        // Finds the object by primary key
        category = catalogService.findCategory(id);
        assertEquals(category.getName(), "Fish");

        // Updates the object
        category.setName("Big Fish");
        catalogService.updateCategory(category);

        // Finds the object by primary key
        category = catalogService.findCategory(id);
        assertEquals(category.getName(), "Big Fish");

        // Deletes the object
        catalogService.removeCategory(category);

        // Checks the object has been deleted
        assertNull("Should has been deleted", catalogService.findCategory(id));
    }
}
