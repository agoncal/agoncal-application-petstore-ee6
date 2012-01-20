package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.service.CustomerService;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
@SessionScoped
public class LoginController extends Controller implements Serializable {

    @Inject
    private Credentials credentials;

    @Inject
    private CustomerService customerService;

    private Customer customer;

    public String doSignIn() {

        String navigateTo = null;
        try {
            Customer customer = customerService.findCustomer(credentials.getLogin());

            // Check if it's the right password
            if (customer != null)
                customer.matchPassword(credentials.getPassword());

            this.customer = customer;
            navigateTo = "main.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doSignIn", e);
        }
        return navigateTo;
    }

    @Named
    @Produces
    @LoggedIn
    public Customer getLoggedInUser() {
        return customer;
    }
}
