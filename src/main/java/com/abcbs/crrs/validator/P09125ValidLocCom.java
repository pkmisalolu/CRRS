package com.abcbs.crrs.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = P09125LocationValidator.class)
public @interface P09125ValidLocCom {


	String message()  default "INVALID LOCATION CODE - FOR VALID CODES PRESS HELP ";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
