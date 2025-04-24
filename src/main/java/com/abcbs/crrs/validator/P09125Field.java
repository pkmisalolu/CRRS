package com.abcbs.crrs.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = P09125FieldValidator.class)
public @interface P09125Field {

	String message() ;

	String fieldName();
	
	Class<?>[] groups() default {}; 

    Class<? extends Payload>[] payload() default {};

}
