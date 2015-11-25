package io.diepet.labs.tourist.core.impl;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.AbstractTouristStackItem;
import io.diepet.labs.tourist.core.api.TouristStackItemFactory;

public class TouristStackItemFactoryImpl implements TouristStackItemFactory {
	
	public AbstractTouristStackItem createNewInstance(ProceedingJoinPoint proceedingJoinPoint) {
		return new DefaultTouristStackItemImpl(proceedingJoinPoint);
	}
}

