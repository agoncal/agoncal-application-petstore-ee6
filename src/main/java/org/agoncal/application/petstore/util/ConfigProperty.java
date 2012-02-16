package org.agoncal.application.petstore.util;

import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author blep
 *         --
 */

@Qualifier
@Target({METHOD, FIELD,PARAMETER})
@Retention(RUNTIME)
public @interface ConfigProperty {
    String value() default "";
}
