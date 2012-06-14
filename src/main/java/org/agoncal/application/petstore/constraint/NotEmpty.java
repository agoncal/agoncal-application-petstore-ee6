package org.agoncal.application.petstore.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@NotNull
@Size(min = 1)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface NotEmpty {

    // ======================================
    // =             Attributes             =
    // ======================================

    String message() default "validator.notEmpty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
