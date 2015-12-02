package io.diepet.labs.tourist.core.api;

import org.junit.Test;

public class TouristImplTests {

	@Test
	public void testTourist() {
		TouristImpl tourist = new TouristImpl();
		tourist.setCameraRollFactory(new CameraRollFactoryImpl());

	}

}
