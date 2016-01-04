package io.tourist.core.condition;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Checks if the proceeding join point class has an annotation.
 */

public final class HasClassAnnotationCondition implements Condition {

	/** The annotation class. */
	@SuppressWarnings("rawtypes")
	private Class clazz;

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.condition.Condition#check(org.aspectj.lang.
	 * ProceedingJoinPoint)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean check(final ProceedingJoinPoint proceedingJoinPoint) {
		return proceedingJoinPoint.getTarget().getClass().isAnnotationPresent(clazz);
	}

	/**
	 * Sets the clazz.
	 *
	 * @param clazz
	 *            the new clazz
	 */
	public void setClazz(@SuppressWarnings("rawtypes") final Class clazz) {
		this.clazz = clazz;
	}

}
