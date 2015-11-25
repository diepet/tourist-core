package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TouristStack {

	void push(ProceedingJoinPoint pjp);
	void pop(Object returnObject);
	void exceptionThrown(Throwable e);
	
}
