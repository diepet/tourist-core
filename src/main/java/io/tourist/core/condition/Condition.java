package io.tourist.core.condition;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * The Condition interface.
 */
public interface Condition {

	/**
	 * Checks if there is a condition from a proceeding join point.
	 *
	 * @param proceedingJoinPoint
	 *            the proceeding join point
	 * @return true, if successful
	 */
	boolean check(ProceedingJoinPoint proceedingJoinPoint);

}
