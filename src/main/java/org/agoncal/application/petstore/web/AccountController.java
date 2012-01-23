package org.agoncal.application.petstore.web;

import org.agoncal.application.petstore.constraint.Login;
import org.agoncal.application.petstore.domain.Address;
import org.agoncal.application.petstore.domain.Customer;
import org.agoncal.application.petstore.service.CustomerService;
import org.agoncal.application.petstore.util.Loggable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
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

    @Inject
    private Conversation conversation;

    private boolean isLoggedIn = false;
    @Login
    private String login;
    private String password;
    private String password2;

    private Customer customer;
    private Address homeAddress;

    // ======================================
    // =             Constants              =
    // ======================================

    // ======================================
    // =            Constructors            =
    // ======================================

    // ======================================
    // =              Public Methods        =
    // ======================================

    @PostConstruct
    private void init() {
        System.out.println();
    }

    @PreDestroy
    private void clear() {
        System.out.println();
    }

    public String doSignIn() {

        String navigateTo = null;
        try {
            Customer customer = customerService.findCustomer(login);

            // Check if it's the right password
            if (customer != null)
                customer.matchPassword(password);

            // Start conversation
            if (conversation.isTransient()) {
                conversation.begin();
            }

            this.customer = customer;
            this.homeAddress = customer.getHomeAddress();
            isLoggedIn = true;
            navigateTo = "main.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doSignIn", e);
        }
        return navigateTo;
    }

    public String doCreateNewAccount() {

        // Login has to be unique
        if (customerService.doesLoginAlreadyExist(customer.getLogin())) {
            addWarningMessage("Login already exists");
            return null;
        }

        // Id and password must be filled
        if ("".equals(customer.getLogin()) || "".equals(customer.getPassword()) || "".equals(password2)) {
            addWarningMessage("Id and passwords have to be filled");
            return null;
        } else if (!customer.getPassword().equals(password2)) {
            addWarningMessage("Both entered passwords have to be the same");
            return null;
        }

        return "createaccount.xhtml";
    }

    public String doCreateCustomer() {
        String navigateTo = null;

        try {
            // Creates the customer
            customer = customerService.createCustomer(customer);
            homeAddress = customer.getHomeAddress();

            // Start conversation
            if (conversation.isTransient()) {
                conversation.begin();
            }

            isLoggedIn = true;
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

    public String doSignOff() {
        customer = null;
        isLoggedIn = false;

        // Stop conversation
        if (!conversation.isTransient()) {
            conversation.end();
        }

        return "main.xhtml";
    }

    // ======================================
    // =          Protected Methods         =
    // ======================================

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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    // ======================================
    // =           Private Methods          =
    // ======================================

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================
}