package io.diepet.labs.tourist.core.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.Tour;

public class DefaultConcreteTour extends Tour {

	DefaultConcreteTour(ProceedingJoinPoint proceedingJoinPoint) {
		super(proceedingJoinPoint);
	}

	@Override
	protected Map<String, BigDecimal> createShotMap() {
		return new HashMap<String, BigDecimal>();
	}

	@Override
	public BigDecimal getShotDefaultValue(String shotName) {
		return BigDecimal.ZERO;
	}
}
