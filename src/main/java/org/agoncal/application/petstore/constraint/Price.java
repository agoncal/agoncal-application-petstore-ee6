package org.agoncal.application.petstore.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@NotNull
@DecimalMin("10")
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface Price {

    // ======================================
    // =             Attributes             =
    // ======================================

    String message() default "{validator.invalidPrice}"; // TODO use message bundle

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
