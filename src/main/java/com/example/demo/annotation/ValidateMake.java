package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.annotation.validator.MakeValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy = MakeValidator.class)
public @interface ValidateMake {

	String message() default "This is not a valid brand";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
