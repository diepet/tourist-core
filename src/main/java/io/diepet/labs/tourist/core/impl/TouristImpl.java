package io.diepet.labs.tourist.core.impl;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.Tourist;
import io.diepet.labs.tourist.core.api.TouristStack;

public class TouristImpl implements Tourist {

	private TouristStack touristStack;
	
	public Object aroundPointcut(ProceedingJoinPoint  pjp) throws Throwable {
		touristStack.push(pjp);
		Object returnObject;
		try {
			returnObject = pjp.proceed();
			touristStack.pop(returnObject);
		} catch (Throwable e) {
			touristStack.exceptionThrown(e);
			throw e;
		}
		return returnObject;
	}
	
}
