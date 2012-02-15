package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.service.CustomerService;
import org.agoncal.application.petstore.util.LoggingProducer;
import org.agoncal.application.petstore.web.Credentials;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author blep
 *         Date: 12/02/12
 *         Time: 11:59
 */
//@Ignore
@RunWith(Arquillian.class)
public class LoginModuleTest {


    private static CustomerService customerService;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Credentials.class, SimpleCallbackHandler.class, LoggingProducer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Produces
    public static CustomerService produceMockCustomerService() {
        return customerService;
    }

    @Inject
    private Credentials credentials;

    @Inject
    private SimpleCallbackHandler callbackHandler;

    @Before
    public void setUp() throws Exception {

        String loginFile = getClass().getResource("/petstore-test.login").getFile();
        System.out.println("loginFile = " + loginFile);
        System.setProperty("java.security.auth.login.config", loginFile);
        customerService = mock(CustomerService.class);

    }

    @Test
    public void testLogin() throws Exception {
        System.out.println("LoginModuleTest.testLogin");

        String login = "admin";
        String password = "password";

        credentials.setLogin(login);
        credentials.setPassword(password);

        when(customerService.findCustomer(login, password)).thenReturn(new Customer());

        LoginContext loginContext = new LoginContext("SimpleLoginModule", callbackHandler);
        loginContext.login();

        verify(customerService).findCustomer(login, password);
    }

    @Test(expected = LoginException.class)
    public void testWrongLogin() throws LoginException {
        String login = "Bob Marley";
        String password = "no woman no cry";

        credentials.setLogin(login);
        credentials.setPassword(password);

        LoginContext loginContext = null;
        try {
            loginContext = new LoginContext("SimpleLoginModule", callbackHandler);
        } catch (LoginException e) {
            e.printStackTrace();
            fail();
        }


        try {
            loginContext.login();
        } catch (LoginException e) {
            verify(customerService).findCustomer(login, password);
            throw e;
        }

        fail();
    }
}
