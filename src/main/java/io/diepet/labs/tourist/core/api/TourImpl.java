package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The default implementation of {@link Tour} interface.
 */
final class TourImpl implements Tour {

	/** The proceeding join point. */
	private ProceedingJoinPoint proceedingJoinPoint;

	/** The result. */
	private Object result;

	/** The fail cause. */
	private Throwable failCause;

	/** The camera roll. */
	private CameraRoll cameraRoll;

	/**
	 * Instantiates a new tour impl.
	 *
	 * @param proceedingJoinPoint
	 *            the proceeding join point
	 */
	TourImpl(ProceedingJoinPoint proceedingJoinPoint) {
		super();
		this.proceedingJoinPoint = proceedingJoinPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Tour#getProceedingJoinPoint()
	 */
	public ProceedingJoinPoint getProceedingJoinPoint() {
		return proceedingJoinPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Tour#getResult()
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 *            the new result
	 */
	void setResult(Object result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Tour#getFailCause()
	 */
	public Throwable getFailCause() {
		return failCause;
	}

	/**
	 * Sets the fail cause.
	 *
	 * @param failCause
	 *            the new fail cause
	 */
	void setFailCause(Throwable failCause) {
		this.failCause = failCause;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Tour#getCameraRoll()
	 */
	public CameraRoll getCameraRoll() {
		return cameraRoll;
	}

	/**
	 * Sets the camera roll.
	 *
	 * @param cameraRoll
	 *            the new camera roll
	 */
	void setCameraRoll(CameraRoll cameraRoll) {
		this.cameraRoll = cameraRoll;
	}
}
