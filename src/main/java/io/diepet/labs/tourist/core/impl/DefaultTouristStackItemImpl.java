package io.diepet.labs.tourist.core.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.AbstractTouristStackItem;

public class DefaultTouristStackItemImpl extends AbstractTouristStackItem {

	public DefaultTouristStackItemImpl(ProceedingJoinPoint proceedingJoinPoint) {
		super(proceedingJoinPoint);
	}

	@Override
	protected Map<String, BigDecimal> createNewShotMap() {
		return new HashMap<String, BigDecimal>();
	}

	@Override
	public BigDecimal getDefaultValueForShot(String shotName) {
		return BigDecimal.ZERO;
	}

}
