package com.example.demo.annotation.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.TrackExecution;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
@Aspect
public class TrackExecutionAdvice {

	@Around("@annotation(com.example.demo.annotation.TrackExecution)")
	public Object trackExecutionSteps(ProceedingJoinPoint point) throws Throwable {

		Object proceed = point.proceed();

		try {
			if (getAnnotation(point).enable())
				LOG_INFO(point, proceed);
		} catch (Exception e) {
			System.out.println("couldn't log something went wrong");
		}

		return proceed;
	}

	private TrackExecution getAnnotation(ProceedingJoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(TrackExecution.class);
	}

	private void LOG_INFO(ProceedingJoinPoint point, Object proceed) throws JsonProcessingException {
		String[] paramNames = ((MethodSignature) point.getSignature()).getParameterNames();
		Object[] paramValues = point.getArgs();

		StringBuilder sr = new StringBuilder();
		sr.append(point.getSignature().getDeclaringTypeName()).append("." + point.getSignature().getName()).append("(");

		for (int i = 0; i < paramNames.length; i++) {
			sr.append(paramNames[i] + "=" + paramValues[i]);
			if (i < paramNames.length - 1) {
				sr.append(", ");
			}
		}
		sr.append(")");
		System.out.println("Method: " + sr.toString());

		 System.out.println("Response: " + proceed);
	}

}
