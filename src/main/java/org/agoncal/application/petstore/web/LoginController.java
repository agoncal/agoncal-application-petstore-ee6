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

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private CustomerService customerService;

    @Inject
    private Credentials credentials;

    @Produces @LoggedIn
    private Customer loggedinCustomer;

    // ======================================
    // =              Public Methods        =
    // ======================================

    public String doLogin() {

        String navigateTo = null;
        try {
            loggedinCustomer = customerService.findCustomer(credentials.getLogin(), credentials.getPassword());
            navigateTo = "main.xhtml";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doLogin", e);
        }
        return navigateTo;
    }

    public String doCreateNewAccount() {

        // Login has to be unique
        if (customerService.doesLoginAlreadyExist(credentials.getLogin())) {
            addWarningMessage("Login already exists");
            return null;
        }

        // Id and password must be filled
        if ("".equals(credentials.getLogin()) || "".equals(credentials.getPassword()) || "".equals(credentials.getPassword2())) {
            addWarningMessage("Id and passwords have to be filled");
            return null;
        } else if (!credentials.getPassword().equals(credentials.getPassword2())) {
            addWarningMessage("Both entered passwords have to be the same");
            return null;
        }

        return "createaccount.xhtml";
    }

    public void doLogout() {
        loggedinCustomer = null;
    }

    public String doUpdateAccount() {

        String navigateTo = null;

        try {
            // Updates the customer
            loggedinCustomer = customerService.updateCustomer(loggedinCustomer);
            addInformationMessage("Your account has been updated");
            navigateTo = "account.updated";
        } catch (Exception e) {
            addMessage(this.getClass().getName(), "doUpdateAccount", e);
        }
        return navigateTo;
    }

    public boolean isLoggedIn() {
        return loggedinCustomer != null;
    }


    public Customer getLoggedinCustomer() {
        return loggedinCustomer;
    }

    public void setLoggedinCustomer(Customer loggedinCustomer) {
        this.loggedinCustomer = loggedinCustomer;
    }
}
