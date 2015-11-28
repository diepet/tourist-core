package io.diepet.labs.tourist.core.api;

import java.math.BigDecimal;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

abstract public class Tour {

	private ProceedingJoinPoint proceedingJoinPoint;
	private Object result;
	private Throwable failCause;
	private Map<String, BigDecimal> shotMap;

	public Tour(ProceedingJoinPoint proceedingJoinPoint) {
		super();
		this.proceedingJoinPoint = proceedingJoinPoint;
	}

	public ProceedingJoinPoint getProceedingJoinPoint() {
		return proceedingJoinPoint;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Map<String, BigDecimal> getShotMap() {
		return shotMap;
	}

	public void setShotMap(Map<String, BigDecimal> shotMap) {
		this.shotMap = shotMap;
	}

	public Throwable getFailCause() {
		return failCause;
	}

	public void setFailCause(Throwable failCause) {
		this.failCause = failCause;
	}

	abstract protected Map<String, BigDecimal> createShotMap();

	abstract public BigDecimal getShotDefaultValue(String shotName);
}
