package org.agoncal.application.petstore.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

// This should work but it looks like there is a bug in GlassFish embedded.
// So I've moved it to DBPopulator for now

//@DataSourceDefinition(name = "java:global/jdbc/applicationPetstoreDS",
//        className = "org.apache.derby.jdbc.ClientDataSource",
//        portNumber = 1527,
//        serverName = "localhost",
//        databaseName = "applicationPetstoreDB",
//        user = "app",
//        password = "app",
//        properties = {"createDatabase=create"}
//)
//@DataSourceDefinition(name = "java:global/jdbc/applicationPetstoreDS",
//        className = "org.apache.derby.jdbc.EmbeddedDriver",
//        url = "jdbc:derby:memory:applicationPetstoreDB;create=true;user=app;password=app"
//)
public class DatabaseProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Produces
    @PersistenceContext(unitName = "applicationPetstorePU")
    private EntityManager em;

}
