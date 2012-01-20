package org.agoncal.application.petstore.domain;

import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

public abstract class AbstractDomainTest {

    // ======================================
    // =             Attributes             =
    // ======================================
    protected static ValidatorFactory vdf;
    protected static Validator validator;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void init() throws Exception {
        vdf = Validation.buildDefaultValidatorFactory();
        validator = vdf.getValidator();
    }
}