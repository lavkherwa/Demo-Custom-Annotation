package com.example.demo.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface TrackExecution {

	/*
	 * This annotation will track and log the execution of different methods and
	 * classes
	 * 
	 * Corresponding advice is implemented at {@link ExecutionTimeAdvice}
	 * 
	 */
	boolean enable();

}
