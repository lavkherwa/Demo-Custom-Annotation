package com.example.demo.annotation.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.TrackExecutionTime;

@Component
@Aspect
public class TrackExecutionTimeAdvice {

	@Around("@annotation(com.example.demo.annotation.TrackExecutionTime)")
	public Object trackTime(ProceedingJoinPoint point) throws Throwable {

		long startTime = 0;
		long endTime = 0;

		if (getAnnotation(point).enable())
			startTime = System.currentTimeMillis();

		Object proceed = point.proceed();

		if (getAnnotation(point).enable()) {
			endTime = System.currentTimeMillis();
			System.out.println("Time spent at " + prepareMessage(point) + " is " + (endTime - startTime) + "ms.");
		}

		return proceed;
	}

	private TrackExecutionTime getAnnotation(ProceedingJoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(TrackExecutionTime.class);
	}

	private String prepareMessage(ProceedingJoinPoint point) {
		String[] paramNames = ((MethodSignature) point.getSignature()).getParameterNames();
		Object[] paramValues = point.getArgs();

		StringBuilder sb = new StringBuilder();
		sb.append(point.getSignature().getDeclaringTypeName()).append("." + point.getSignature().getName()).append("(");

		for (int i = 0; i < paramNames.length; i++) {
			sb.append(paramNames[i] + "=" + paramValues[i]);
			if (i < paramNames.length - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");

		return sb.toString();
	}

}
