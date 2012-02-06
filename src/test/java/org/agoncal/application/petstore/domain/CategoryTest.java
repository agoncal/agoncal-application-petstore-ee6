package org.agoncal.application.petstore.domain;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 */
public class CategoryTest extends AbstractDomainTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldCreateAValidCategory() {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");

        // Checks the object is valid
        assertEquals("Should have not constraint violation", 0, validator.validate(category).size());
    }

    @Test
    public void shouldBeAbleToMarshallAndUnmarchallIntoXML() throws Exception {

        // Creates an object
        Category category = new Category("Fish", "Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body");

        // Marshalls it to XML
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(Category.class);
        Marshaller m = context.createMarshaller();
        m.marshal(category, writer);
    }
}