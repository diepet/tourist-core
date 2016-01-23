package io.tourist.core.condition;

import java.lang.annotation.Annotation;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Checks if the proceeding join point class has an annotation.
 */

public final class HasClassAnnotationCondition implements Condition {

	/** The annotation class. */
	private String clazz;

	/** The deep search interfaces flag. */
	private boolean deepSearchInterfaces;

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.condition.Condition#check(org.aspectj.lang.
	 * ProceedingJoinPoint)
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public boolean check(final ProceedingJoinPoint proceedingJoinPoint) {

		boolean result = false;
		Class targetClass = proceedingJoinPoint.getTarget().getClass();
		if (isAnnotationPresent(targetClass)) {
			result = true;
		} else if (this.deepSearchInterfaces) {
			Class[] interfaces = targetClass.getInterfaces();
			for (Class i : interfaces) {
				if (isAnnotationPresent(i)) {
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
	public void setClazz(final String clazz) {
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

	/**
	 * Checks if is annotation present.
	 *
	 * @param someClass
	 *            the some class
	 * @return true, if is annotation present
	 */
	private boolean isAnnotationPresent(@SuppressWarnings("rawtypes") Class someClass) {
		boolean result = false;
		Annotation[] annotations = someClass.getAnnotations();
		for (Annotation annotation : annotations) {
			if (this.clazz.equals(annotation.annotationType().getName())) {
				result = true;
				break;
			}
		}
		return result;
	}

}
