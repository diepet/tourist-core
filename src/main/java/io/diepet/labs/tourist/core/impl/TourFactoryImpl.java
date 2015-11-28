package io.diepet.labs.tourist.core.impl;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.Tour;
import io.diepet.labs.tourist.core.api.TourFactory;

public class TourFactoryImpl implements TourFactory {

	public Tour createNewInstance(ProceedingJoinPoint proceedingJoinPoint) {
		return new DefaultConcreteTour(proceedingJoinPoint);
	}

}
