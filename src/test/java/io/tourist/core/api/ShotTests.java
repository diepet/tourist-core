package io.tourist.core.api;

import org.junit.Assert;
import org.junit.Test;

import io.tourist.core.api.Shot;
import io.tourist.core.api.ShotImpl;

public class ShotTests {

	@Test
	public void testNewShotInstance() {
		long currentTime = System.currentTimeMillis();
		Shot shot = new ShotImpl("Picture");
		Assert.assertEquals("Picture", shot.getPicture());
		Assert.assertTrue(currentTime <= shot.getTimestamp());
	}

}
