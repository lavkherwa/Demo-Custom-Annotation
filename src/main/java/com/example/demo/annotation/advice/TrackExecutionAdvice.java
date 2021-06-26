package com.example.demo.annotation.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.example.demo.annotation.TrackExecution;

@Component
@Aspect
public class TrackExecutionAdvice {

	@Around("@annotation(com.example.demo.annotation.TrackExecution)")
	public Object trackExecutionSteps(ProceedingJoinPoint point) throws Throwable {

		LOG_INPUT(point, false);

		Object proceed = point.proceed();

		try {
			if (getAnnotation(point).enable())
				LOG_RESPONSE(point, proceed, true);
		} catch (Exception e) {
			System.out.println("couldn't log; something went wrong");
		}

		return proceed;
	}

	private TrackExecution getAnnotation(ProceedingJoinPoint point) {
		MethodSignature signature = (MethodSignature) point.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(TrackExecution.class);
	}

	private void LOG_INPUT(ProceedingJoinPoint point, boolean isResponse) {
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
		
		if(isResponse) {
			System.out.println("Execution Ended: " + sb.toString());
		}else {
			System.out.println("Execution Started: " + sb.toString());
		}
			
	}

	private void LOG_RESPONSE(ProceedingJoinPoint point, Object proceed, boolean isResponse) {
		LOG_INPUT(point, isResponse);
		System.out.println("Response: " + proceed);
	}

}
