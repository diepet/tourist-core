package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TouristStackItemFactory {

	AbstractTouristStackItem createNewInstance(ProceedingJoinPoint proceedingJoinPoint);
	
}
