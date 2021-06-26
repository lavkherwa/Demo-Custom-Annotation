package com.example.demo.annotation.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.annotation.ValidateMake;

public class MakeValidator implements ConstraintValidator<ValidateMake, String> {

	List<String> validMake = Arrays.asList("Audi", "BMW", "Porsche", "Mercedes");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return validMake.contains(value);

	}

}
