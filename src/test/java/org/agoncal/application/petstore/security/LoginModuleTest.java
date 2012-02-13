package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.service.AbstractServiceIT;
import org.agoncal.application.petstore.service.CustomerService;
import org.agoncal.application.petstore.util.LoggingProducer;
import org.agoncal.application.petstore.web.AccountController;
import org.agoncal.application.petstore.web.Controller;
import org.agoncal.application.petstore.web.Credentials;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.login.LoginContext;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author blep
 *         Date: 12/02/12
 *         Time: 11:59
 */
//@Ignore
@RunWith(Arquillian.class)
public class LoginModuleTest extends AbstractServiceIT{


/*
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Credentials.class, SimpleCallbackHandler.class, LoggingProducer.class)
                .addClasses(CustomerService.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }
*/

    /*@Named
    public static class MockAccountController extends AccountController{

    }
*/

    @Inject
    private Credentials credentials;

    @Inject
    private SimpleCallbackHandler callbackHandler;

//    @Inject
//    BeanManager beanManager;


    @Before
    public void setUp() throws Exception {

        String loginFile = getClass().getResource("/petstore-test.login").getFile();
        System.out.println("loginFile = " + loginFile);
        System.setProperty("java.security.auth.login.config", loginFile);
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println("LoginModuleTest.testLogin");

        credentials.setLogin("admin");
        credentials.setPassword("password");
        
        LoginContext loginContext = new LoginContext("SimpleLoginModule", callbackHandler);
        loginContext.login();
    }
}
