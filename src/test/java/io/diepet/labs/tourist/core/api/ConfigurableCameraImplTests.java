package io.diepet.labs.tourist.core.api;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ConfigurableCameraImplTests {

	@Test
	public void testCamera() {
		CameraRollImpl cameraRoll = new CameraRollImpl();
		ConfigurableCameraImpl camera = new ConfigurableCameraImpl();

		// isOn() method check
		Assert.assertFalse(camera.isOn());
		CameraRoll previousCameraRoll = camera.replaceCameraRoll(cameraRoll);
		Assert.assertNull(previousCameraRoll);
		Assert.assertTrue(camera.isOn());

		// shot check
		Assert.assertTrue(camera.shot("First picture"));
		Assert.assertTrue(camera.shot("Second picture"));
		Assert.assertTrue(camera.shot("Third picture"));
		previousCameraRoll = camera.replaceCameraRoll(null);
		Assert.assertFalse(camera.isOn());
		List<Shot> shotList = previousCameraRoll.getShotList();
		Iterator<Shot> iterator = shotList.iterator();
		// assert first shot
		Assert.assertTrue(iterator.hasNext());
		Shot firstShot = iterator.next();
		Assert.assertEquals("First picture", firstShot.getPicture());
		// assert second shot
		Assert.assertTrue(iterator.hasNext());
		Shot secondShot = iterator.next();
		Assert.assertEquals("Second picture", secondShot.getPicture());
		Assert.assertTrue(firstShot.getTimestamp() <= secondShot.getTimestamp());
		// assert third shot
		Assert.assertTrue(iterator.hasNext());
		Shot thirdShot = iterator.next();
		Assert.assertEquals("Third picture", thirdShot.getPicture());
		Assert.assertTrue(secondShot.getTimestamp() <= thirdShot.getTimestamp());
		// assert no more shots present
		Assert.assertFalse(iterator.hasNext());

	}

}
