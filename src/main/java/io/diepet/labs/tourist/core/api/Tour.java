package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface Tour {

	public ProceedingJoinPoint getProceedingJoinPoint();

	public Object getResult();

	public Throwable getFailCause();

	public CameraRoll getCameraRoll();
}
