package com.example.demo.annotation.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TrackExecutionTimeAdvice {
	
	@Around("@annotation(com.example.demo.annotation.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint point) throws Throwable {

		long startTime = System.currentTimeMillis();
		Object proceed = point.proceed();
		long endTime = System.currentTimeMillis();

		System.out.println("Time spent at " + point.getSignature() +  " is "  + (endTime - startTime));

		return proceed;
	}

}
