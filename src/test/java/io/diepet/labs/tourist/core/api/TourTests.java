package io.diepet.labs.tourist.core.api;

import org.aspectj.lang.ProceedingJoinPoint;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class TourTests {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private ProceedingJoinPoint proceedingJoinPoint;

	@Test
	public void testNewInstance() {
		EasyMock.replay(this.proceedingJoinPoint);
		TourImpl tour = new TourImpl(proceedingJoinPoint);
		CameraRoll cameraRoll = new CameraRollImpl();
		Object result = new Object();
		Throwable e = new Throwable();

		tour.setCameraRoll(cameraRoll);
		tour.setResult(result);
		tour.setFailCause(e);

		Assert.assertTrue(this.proceedingJoinPoint == tour.getProceedingJoinPoint());
		Assert.assertTrue(cameraRoll == tour.getCameraRoll());
		Assert.assertTrue(result == tour.getResult());
		Assert.assertTrue(e == tour.getFailCause());
	}

}
