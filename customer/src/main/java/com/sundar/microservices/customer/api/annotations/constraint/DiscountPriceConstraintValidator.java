package com.sundar.microservices.customer.api.annotations.constraint;

import com.sundar.microservices.customer.api.annotations.VerifyDiscountedPrice;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class DiscountPriceConstraintValidator implements ConstraintValidator<VerifyDiscountedPrice, Double> {


    @Override
    public void initialize(VerifyDiscountedPrice constraintAnnotation) {

    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {

        if(value != null && value > 0 ) return true;
        return false;
    }
}
