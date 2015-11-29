package io.diepet.labs.tourist.core.event;

import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Rule;
import org.junit.Test;

import io.diepet.labs.tourist.core.api.Tour;

public class TourEventListenerAdapterTests {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private Tour tour;

	@Mock
	private TourEventListenerAdapter tourEventListenerAdapter;

	@Test
	public void testTourEventListenerAdapter() {

		// test tourist travel started event
		EasyMock.reset(tourEventListenerAdapter);
		tourEventListenerAdapter.onTouristTravelStarted(tour);
		EasyMock.expectLastCall();
		EasyMock.replay(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_STARTED, tour));

		// test tour started event
		EasyMock.reset(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourStarted(tour);
		EasyMock.expectLastCall();
		EasyMock.replay(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEvent(new TourEvent(TourEventType.TOUR_STARTED, tour));

		// test tour ended event
		EasyMock.reset(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEnded(tour);
		EasyMock.expectLastCall();
		EasyMock.replay(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEvent(new TourEvent(TourEventType.TOUR_ENDED, tour));

		// test tourist travel ended event
		EasyMock.reset(tourEventListenerAdapter);
		tourEventListenerAdapter.onTouristTravelEnded(tour);
		EasyMock.expectLastCall();
		EasyMock.replay(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour));

		// test tour failed event
		EasyMock.reset(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourFailed(tour);
		EasyMock.expectLastCall();
		EasyMock.replay(tourEventListenerAdapter);
		tourEventListenerAdapter.onTourEvent(new TourEvent(TourEventType.TOUR_FAILED, tour));

	}

}
