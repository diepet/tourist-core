package io.diepet.labs.tourist.core.api;

import org.junit.Assert;
import org.junit.Test;

public class CameraRollFactoryImplTests {

	@Test
	public void testFactory() {
		CameraRollFactoryImpl factory = new CameraRollFactoryImpl();
		CameraRoll firstInstance = factory.createNewInstance();
		Assert.assertTrue(firstInstance instanceof CameraRollImpl);
		CameraRoll secondInstance = factory.createNewInstance();
		Assert.assertTrue(secondInstance instanceof CameraRollImpl);
		Assert.assertFalse(firstInstance == secondInstance);
	}

}
