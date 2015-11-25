package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;

public interface AspectJAroundPointcut {

	public Object aroundPointcut(ProceedingJoinPoint  pjp) throws Throwable;

}
