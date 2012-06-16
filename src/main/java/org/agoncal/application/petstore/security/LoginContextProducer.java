package org.agoncal.application.petstore.security;

import org.agoncal.application.petstore.util.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * @author blep
 *         Date: 16/02/12
 *         Time: 07:28
 */
public class LoginContextProducer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Inject
    private SimpleCallbackHandler callbackHandler;

    // ======================================
    // =          Business methods          =
    // ======================================

    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigFile") String loginConfigFileName,
                                            @ConfigProperty("loginModuleName") String loginModuleName) throws LoginException {

        System.setProperty("java.security.auth.login.config", new File(LoginContextProducer.class.getResource(loginConfigFileName).toURI()).getPath());

        return new LoginContext(loginModuleName, callbackHandler);
    }

}
