package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.constraint.Login;
import org.agoncal.application.petstore.domain.Address;
import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.service.CustomerService;
import org.agoncal.application.petstore.util.Loggable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Named
@Loggable
@SessionScoped
public class AccountController extends Controller implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CustomerService customerService;

    @Login
    private String login;
    private String password;

    @Inject @LoggedIn
    private Customer loggedInCustomer;
    private Customer customer;
    private Address homeAddress;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String doCreateCustomer() {
        String navigateTo = null;

        try {
            // Creates the customer
            customer = customerService.createCustomer(customer);
            homeAddress = customer.getHomeAddress();

            navigateTo = "main.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doCreateCustomer", e);
        }
        return navigateTo;
    }

    public String doFindCustomer() {
        String navigateTo = null;

        try {
            // Creates the customer
            customer = customerService.findCustomer(login);
            homeAddress = customer.getHomeAddress();
            navigateTo = "showaccount.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doFindCustomer", e);
        }
        return navigateTo;
    }

    public String doUpdateAccount() {

        String navigateTo = null;

        try {
            // Updates the customer
            customer = customerService.updateCustomer(customer, homeAddress);
            homeAddress = customer.getHomeAddress();
            navigateTo = "account.updated";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doUpdateAccount", e);
        }
        return navigateTo;
    }

    // ======================================
    // =         Getters & setters          =
    // ======================================
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================
}