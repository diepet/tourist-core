package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

final class TourImpl implements Tour {

	private ProceedingJoinPoint proceedingJoinPoint;
	private Object result;
	private Throwable failCause;
	private CameraRoll cameraRoll;

	TourImpl(ProceedingJoinPoint proceedingJoinPoint) {
		super();
		this.proceedingJoinPoint = proceedingJoinPoint;
	}

	public ProceedingJoinPoint getProceedingJoinPoint() {
		return proceedingJoinPoint;
	}

	public Object getResult() {
		return result;
	}

	void setResult(Object result) {
		this.result = result;
	}

	public Throwable getFailCause() {
		return failCause;
	}

	void setFailCause(Throwable failCause) {
		this.failCause = failCause;
	}

	public CameraRoll getCameraRoll() {
		return cameraRoll;
	}

	void setCameraRoll(CameraRoll cameraRoll) {
		this.cameraRoll = cameraRoll;
	}
}
