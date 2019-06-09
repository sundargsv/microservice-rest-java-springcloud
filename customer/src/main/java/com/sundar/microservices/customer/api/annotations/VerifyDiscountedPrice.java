package com.sundar.microservices.customer.api.annotations;

import com.sundar.microservices.customer.api.annotations.constraint.DiscountPriceConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DiscountPriceConstraintValidator.class)
@Target( { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyDiscountedPrice {

    String message() default "The given discount price is invalid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
