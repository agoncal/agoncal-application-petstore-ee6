package org.agoncal.application.petstore.service;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

public abstract class AbstractServiceTest {

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.agoncal.application.petstore")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
//        System.out.println(archive.toString(true));
        return archive;
    }

}