package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.service.CustomerService;
import org.agoncal.application.petstore.web.AccountController;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

/**
 * @author blep
 *         Date: 12/02/12
 *         Time: 11:59
 */
public class SimpleLoginModule  implements LoginModule{

    private CallbackHandler callbackHandler;

    private CustomerService customerService;

    private BeanManager beanManager;
    
    private CustomerService getCustomerService() {
        if (customerService != null) {
            return customerService;
        }
        try {
            Context context = new InitialContext();
            // not portable if not called from a JEE component
            // -> deltapsike and BeanProvider like solution is far better
            beanManager = (BeanManager) context.lookup("java:comp/BeanManager");
            Bean<?> bean = beanManager.getBeans(CustomerService.class).iterator().next();
            CreationalContext cc = beanManager.createCreationalContext(bean);
            customerService = (CustomerService) beanManager.getReference(bean, CustomerService.class, cc);

        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return customerService;

    }

    public SimpleLoginModule() {
        // no-op
    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> stringMap, Map<String, ?> stringMap1) {
        this.callbackHandler = callbackHandler;
        getCustomerService();
    }

    @Override
    public boolean login() throws LoginException {

        NameCallback nameCallback = new NameCallback("Name : ");
        PasswordCallback passwordCallback = new PasswordCallback("Password : ", false);
        try {
            callbackHandler.handle(new Callback[]{nameCallback,passwordCallback});
            String username = new String(nameCallback.getName());
            String password = new String(passwordCallback.getPassword());
            nameCallback.setName("");
            passwordCallback.clearPassword();
            Customer customer = customerService.findCustomer(username, password);

            if (customer == null) {
                throw new LoginException("Authentication failed");
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean abort() throws LoginException {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean logout() throws LoginException {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
