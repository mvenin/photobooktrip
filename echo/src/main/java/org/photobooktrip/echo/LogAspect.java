package org.photobooktrip.echo;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclarePrecedence;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
// @DeclarePrecedence("LogAspect, *")
public class LogAspect {

	@Pointcut("execution(  * org.photobooktrip..*(..))")
	public void anyMethod() {

	}

	@AfterThrowing(pointcut = "anyMethod()", throwing = "error")
	public void threatException(JoinPoint joinPoint, Throwable error) {
		String fullPath = joinPoint.getSignature().getDeclaringTypeName();
		Object[] argsMethods = joinPoint.getArgs();
		String argsMethodsString = Arrays.toString(joinPoint.getArgs());
		System.out.println(joinPoint);
		System.out.println(error);
	}
}
