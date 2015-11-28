package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TourFactory {
	
	Tour createNewInstance(ProceedingJoinPoint proceedingJoinPoint);

}
