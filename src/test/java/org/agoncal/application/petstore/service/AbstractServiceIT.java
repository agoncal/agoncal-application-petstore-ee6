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

public abstract class AbstractServiceIT {

    // ======================================
    // =             Attributes             =
    // ======================================

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @Deployment
    public static JavaArchive createTestArchive() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true, "org.agoncal.application.petstore")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsResource("META-INF/jboss-deployment-structure.xml", "META-INF/jboss-deployment-structure.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

}