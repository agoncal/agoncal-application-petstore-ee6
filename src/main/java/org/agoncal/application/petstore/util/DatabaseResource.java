package org.agoncal.application.petstore.util;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

//@DataSourceDefinition(name = "java:global/jdbc/applicationPetstoreDS",
//        className = "org.apache.derby.jdbc.ClientDataSource",
//        portNumber = 1527,
//        serverName = "localhost",
//        databaseName = "applicationPetstoreDB",
//        user = "app",
//        password = "app",
//        properties = {"createDatabase=create"}
//)
@DataSourceDefinition(name = "java:global/jdbc/applicationPetstoreDS",
        className = "org.apache.derby.jdbc.EmbeddedDriver",
        url = "jdbc:derby:memory:applicationPetstoreDB;create=true;user=app;password=app"
)
public class DatabaseResource {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Produces
    @PersistenceContext(unitName = "applicationPetstorePU")
    private EntityManager em;

}
