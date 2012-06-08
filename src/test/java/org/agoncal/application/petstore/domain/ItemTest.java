package org.agoncal.application.petstore.domain;

import java.io.StringWriter;
import javax.inject.Inject;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
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
public class ItemTest {
    @Inject
    private Validator validator;

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(Category.class, Product.class, Item.class);
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidItem() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Bulldog", "Friendly dog from England", category);
        Item item = new Item("Thootless fish", 10f, "fish1.gif", product, "desc");

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(item).size());
    }

    @Test
    public void shouldBeAbleToMarshallAndUnmarchallIntoXML() throws Exception {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");
        Product product = new Product("Bulldog", "Friendly dog from England", category);
        Item item = new Item("Thootless fish", 10f, "fish1.gif", product, "desc");

        // Marshalls it to XML
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Item.class);
        Marshaller m = context.createMarshaller();
        m.marshal(item, writer);
    }
}