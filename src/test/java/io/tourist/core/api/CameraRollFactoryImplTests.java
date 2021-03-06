package io.tourist.core.api;

import org.junit.Assert;
import org.junit.Test;

import io.tourist.core.api.CameraRoll;
import io.tourist.core.api.CameraRollFactoryImpl;
import io.tourist.core.api.CameraRollImpl;

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
