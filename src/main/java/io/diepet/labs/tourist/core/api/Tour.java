package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The Tour interface.
 */
public interface Tour {

	/**
	 * Gets the proceeding join point.
	 *
	 * @return the proceeding join point
	 */
	ProceedingJoinPoint getProceedingJoinPoint();

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	Object getResult();

	/**
	 * Gets the fail cause.
	 *
	 * @return the fail cause
	 */
	Throwable getFailCause();

	/**
	 * Gets the camera roll.
	 *
	 * @return the camera roll
	 */
	CameraRoll getCameraRoll();
}
