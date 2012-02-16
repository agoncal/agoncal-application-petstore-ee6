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

    @Inject
    private SimpleCallbackHandler callbackHandler;


    @Produces
    @ConfigProperty("loginModuleName")
    public static String produceLoginModuleName() {
        //TODO externalize this
        return "SimpleLoginModule";
    }

    @Produces
    @ConfigProperty("loginConfigFile")
    public static String produceLoginConfigFile() {
        //TODO externalize this
        return LoginContextProducer.class.getResource("/petstore-test.login").getFile();
    }


    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigFile") String loginConfigFileName,
                                            @ConfigProperty("loginModuleName") String loginModuleName)
            throws LoginException {
        System.setProperty("java.security.auth.login.config", loginConfigFileName);

        return new LoginContext(loginModuleName, callbackHandler);
    }

}
