package org.agoncal.application.petstore.exception;

import javax.ejb.ApplicationException;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 *         Thrown when the Credit Card is not valid
 */

@ApplicationException(rollback = true)
public class CreditCardException extends RuntimeException {

    // ======================================
    // =            Constructors            =
    // ======================================

    public CreditCardException(String message) {
        super(message);
    }
}