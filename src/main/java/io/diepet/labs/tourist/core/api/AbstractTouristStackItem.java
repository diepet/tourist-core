package io.diepet.labs.tourist.core.api;

import java.math.BigDecimal;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

public abstract class AbstractTouristStackItem {

	private ProceedingJoinPoint proceedingJoinPoint;
	private Object returnObject;
	private Map<String, BigDecimal> shotMap;
	
	public AbstractTouristStackItem(ProceedingJoinPoint proceedingJoinPoint) {
		this.proceedingJoinPoint = proceedingJoinPoint;
		shotMap = this.createNewShotMap();
	}
	
	public ProceedingJoinPoint getProceedingJoinPoint() {
		return proceedingJoinPoint;
	}
	
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	
	public Object getReturnObject() {
		return returnObject;
	}
	
	public void putShot(String shotName, BigDecimal value) {
		this.shotMap.put(shotName, value);
	}

	public BigDecimal getShot(String shotName) {
		BigDecimal value;
		if (this.shotMap.containsKey(shotName)) {
			value = this.shotMap.get(shotName);
		} else {
			value = getDefaultValueForShot(shotName);
		}
		return value;
	}
	
	abstract protected Map<String, BigDecimal> createNewShotMap();
	abstract public  BigDecimal getDefaultValueForShot(String shotName);
	
}
