package org.agoncal.application.petstore.domain;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
public class ItemTest extends AbstractDomainTest {

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