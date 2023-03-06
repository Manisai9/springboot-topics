package com.example.demo.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.example.demo.entity.User;

@Aspect
@Component
public class LoggingAspect {
	private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);

	@Before("execution(* com.example.demo.controller..*(..)))")
	public void before(JoinPoint joinPoint) {
		// Advice
		LOGGER.info(" Check for user access ");
		LOGGER.info(" Allowed execution for {}", joinPoint);
	}

	@After("execution(* com.example.demo.service..*(..)))")
	public void after(JoinPoint joinPoint) {
		LOGGER.info("after execution of {}", joinPoint);
	}

	@AfterThrowing("execution(* com.example.demo.service..*(..)))")
	public void logExceptions(JoinPoint joinPoint) {
		LOGGER.info("Exception thrown in Employee Method=" + joinPoint.toString());
	}

	@AfterReturning(value = "execution(* com.example.demo..*(..))", returning = "user")
	public void afterReturning(JoinPoint joinPoint, User user) {
		LOGGER.info("after method invoked:" +user);

	}

	@Around("execution(* com.example.demo..*(..)))")
	public Object aroundAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

		// Get intercepted method details
		String className = methodSignature.getDeclaringType().getSimpleName();
		String methodName = methodSignature.getName();

		final StopWatch stopWatch = new StopWatch();

		// Measure method execution time
		stopWatch.start();
		Object result = proceedingJoinPoint.proceed();
		stopWatch.stop();

		// Log method execution time
		LOGGER.info("Execution time of " + className + "." + methodName + " " + ":: " + stopWatch.getTotalTimeMillis()
				+ " ms");

		return result;
	}
}