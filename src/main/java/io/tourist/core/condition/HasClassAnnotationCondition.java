package io.tourist.core.condition;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Checks if the proceeding join point class has an annotation.
 */

public final class HasClassAnnotationCondition implements Condition {

	/** The annotation class. */
	@SuppressWarnings("rawtypes")
	private Class clazz;

	/** The deep search interfaces flag. */
	private boolean deepSearchInterfaces;

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.condition.Condition#check(org.aspectj.lang.
	 * ProceedingJoinPoint)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean check(final ProceedingJoinPoint proceedingJoinPoint) {

		boolean result = false;
		Class targetClass = proceedingJoinPoint.getTarget().getClass();
		if (targetClass.isAnnotationPresent(this.clazz)) {
			result = true;
		} else if (this.deepSearchInterfaces) {
			Class[] interfaces = targetClass.getInterfaces();
			for (Class i : interfaces) {
				if (i.isAnnotationPresent(this.clazz)) {
					result = true;
					break;
				}
			}
		}
		return result;
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

	/**
	 * Sets the deep search interfaces flag: if true, the annotation will be
	 * searched in the implemented interfaces too.
	 *
	 * @param deepSearchInterfaces
	 *            the new deep search interfaces flag
	 */
	public void setDeepSearchInterfaces(final boolean deepSearchInterfaces) {
		this.deepSearchInterfaces = deepSearchInterfaces;
	}
}
