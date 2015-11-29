package io.diepet.labs.tourist.core.event;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import io.diepet.labs.tourist.core.api.Tour;

public class TourEventTests {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private Tour tour;

	@Test
	public void testNewInstance() {
		EasyMock.replay(tour);
		TourEvent tourEvent;

		tourEvent = new TourEvent(TourEventType.TOURIST_TRAVEL_STARTED, tour);
		Assert.assertTrue(tourEvent.getTour() == this.tour);
		Assert.assertEquals(TourEventType.TOURIST_TRAVEL_STARTED, tourEvent.getTourEventType());

		tourEvent = new TourEvent(TourEventType.TOUR_STARTED, tour);
		Assert.assertTrue(tourEvent.getTour() == this.tour);
		Assert.assertEquals(TourEventType.TOUR_STARTED, tourEvent.getTourEventType());

		tourEvent = new TourEvent(TourEventType.TOUR_ENDED, tour);
		Assert.assertTrue(tourEvent.getTour() == this.tour);
		Assert.assertEquals(TourEventType.TOUR_ENDED, tourEvent.getTourEventType());

		tourEvent = new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour);
		Assert.assertTrue(tourEvent.getTour() == this.tour);
		Assert.assertEquals(TourEventType.TOURIST_TRAVEL_ENDED, tourEvent.getTourEventType());

		tourEvent = new TourEvent(TourEventType.TOUR_FAILED, tour);
		Assert.assertTrue(tourEvent.getTour() == this.tour);
		Assert.assertEquals(TourEventType.TOUR_FAILED, tourEvent.getTourEventType());

	}

}
