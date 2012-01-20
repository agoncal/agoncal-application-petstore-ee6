package org.agoncal.application.petstore.service;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.SQLException;

import static org.agoncal.application.petstore.util.Constants.PERSISTENCE_UNIT_NAME;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

public abstract class AbstractServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initEntityManager() throws Exception {
    }

    @AfterClass
    public static void closeEntityManager() throws SQLException {
    }
}