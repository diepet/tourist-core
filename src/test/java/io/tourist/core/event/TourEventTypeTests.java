package io.tourist.core.event;

import org.junit.Assert;
import org.junit.Test;

import io.tourist.core.event.TourEventType;

public class TourEventTypeTests {

	@Test
	public void testTourEventType() {
		Assert.assertEquals(TourEventType.TOURIST_TRAVEL_STARTED, TourEventType.valueOf("TOURIST_TRAVEL_STARTED"));
		Assert.assertEquals(TourEventType.TOUR_STARTED, TourEventType.valueOf("TOUR_STARTED"));
		Assert.assertEquals(TourEventType.TOUR_ENDED, TourEventType.valueOf("TOUR_ENDED"));
		Assert.assertEquals(TourEventType.TOUR_FAILED, TourEventType.valueOf("TOUR_FAILED"));
		Assert.assertEquals(TourEventType.TOURIST_TRAVEL_ENDED, TourEventType.valueOf("TOURIST_TRAVEL_ENDED"));

	}

}
