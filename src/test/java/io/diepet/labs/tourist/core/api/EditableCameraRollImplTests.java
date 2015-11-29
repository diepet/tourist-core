package io.diepet.labs.tourist.core.api;

import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EditableCameraRollImplTests {

	@Test
	public void testShot() {
		CameraRollImpl cameraRoll = new CameraRollImpl();
		boolean addShotResult = false;
		addShotResult = cameraRoll.addShot(new ShotImpl("First picture"));
		Assert.assertTrue(addShotResult);
		addShotResult = cameraRoll.addShot(new ShotImpl("Second picture"));
		Assert.assertTrue(addShotResult);
		addShotResult = cameraRoll.addShot(new ShotImpl("Third picture"));
		Assert.assertTrue(addShotResult);
		List<Shot> shotList = cameraRoll.getShotList();
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

	@Test(expected = UnsupportedOperationException.class)
	public void testUnmodifiableShotList() {
		CameraRollImpl cameraRoll = new CameraRollImpl();
		boolean addShotResult = false;
		addShotResult = cameraRoll.addShot(new ShotImpl("First picture"));
		Assert.assertTrue(addShotResult);
		addShotResult = cameraRoll.addShot(new ShotImpl("Second picture"));
		Assert.assertTrue(addShotResult);
		addShotResult = cameraRoll.addShot(new ShotImpl("Third picture"));
		Assert.assertTrue(addShotResult);
		List<Shot> shotList = cameraRoll.getShotList();
		Assert.assertEquals(3, shotList.size());
		shotList.add(new ShotImpl("Fourth picture"));
	}

}
