package io.tourist.core.api;

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

	/** The duration. */
	private long duration;

	/**
	 * Instantiates a new tour impl.
	 *
	 * @param proceedingJoinPoint
	 *            the proceeding join point
	 */
	TourImpl(final ProceedingJoinPoint proceedingJoinPoint) {
		super();
		this.proceedingJoinPoint = proceedingJoinPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tour#getProceedingJoinPoint()
	 */
	@Override
	public ProceedingJoinPoint getProceedingJoinPoint() {
		return proceedingJoinPoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tour#getResult()
	 */
	@Override
	public Object getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 *            the new result
	 */
	void setResult(final Object result) {
		this.result = result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tour#getFailCause()
	 */
	@Override
	public Throwable getFailCause() {
		return failCause;
	}

	/**
	 * Sets the fail cause.
	 *
	 * @param failCause
	 *            the new fail cause
	 */
	void setFailCause(final Throwable failCause) {
		this.failCause = failCause;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tour#getCameraRoll()
	 */
	@Override
	public CameraRoll getCameraRoll() {
		return cameraRoll;
	}

	/**
	 * Sets the camera roll.
	 *
	 * @param cameraRoll
	 *            the new camera roll
	 */
	void setCameraRoll(final CameraRoll cameraRoll) {
		this.cameraRoll = cameraRoll;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tour#getDuration()
	 */
	@Override
	public long getDuration() {
		return this.duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration
	 *            the new duration
	 */
	public void setDuration(final long duration) {
		this.duration = duration;
	}
}
