package org.agoncal.application.petstore.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@ApplicationPath("/rs")
public class ApplicationConfig extends Application {

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CatalogRestService.class);
        return classes;
    }
}
