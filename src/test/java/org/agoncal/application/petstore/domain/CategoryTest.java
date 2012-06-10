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
public class CategoryTest {
    @Inject
    private Validator validator;

    @Deployment
    public static JavaArchive jar() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Category.class);
    }

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