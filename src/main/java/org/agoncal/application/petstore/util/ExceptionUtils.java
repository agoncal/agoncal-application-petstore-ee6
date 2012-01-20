package org.agoncal.application.petstore.util;

import org.agoncal.application.petstore.exception.ValidationException;

import javax.ejb.EJBException;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

public class ExceptionUtils {

    // ======================================
    // =              Public Methods        =
    // ======================================

    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause;

        if (throwable instanceof EJBException) {
            cause = ((EJBException) throwable).getCausedByException();
        } else {
            cause = throwable.getCause();
        }

        if (cause != null) {
            throwable = cause;
            while ((throwable = throwable.getCause()) != null) {
                cause = throwable;
            }
        }
        return cause;
    }

    public static boolean isApplicationException(Throwable throwable) {
        if (throwable instanceof ValidationException) {
            return true;
        } else {
            return false;
        }
    }
}
