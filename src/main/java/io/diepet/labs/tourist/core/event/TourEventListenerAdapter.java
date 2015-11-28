package io.diepet.labs.tourist.core.event;

import io.diepet.labs.tourist.core.api.Tour;

abstract public class TourEventListenerAdapter implements TourEventListener {

	@Override
	public void onTourEvent(TourEvent tourEvent) {
		switch (tourEvent.getTourEventType()) {

		case TOURIST_TRAVEL_STARTED:
			this.onTouristTravelStarted(tourEvent.getTour());
			break;

		case TOUR_STARTED:
			this.onTourStarted(tourEvent.getTour());
			break;

		case TOUR_ENDED:
			this.onTourEnded(tourEvent.getTour());
			break;

		case TOURIST_TRAVEL_ENDED:
			this.onTouristTravelEnded(tourEvent.getTour());
			break;

		case TOUR_FAILED:
			this.onTourFailed(tourEvent.getTour());
			break;

		}
	}

	public void onTouristTravelStarted(Tour tour) {
		// empty implementation
	}

	public void onTourStarted(Tour tour) {
		// empty implementation
	}

	public void onTourEnded(Tour tour) {
		// empty implementation
	}

	public void onTouristTravelEnded(Tour tour) {
		// empty implementation
	}

	public void onTourFailed(Tour tour) {
		// empty implementation
	}
}
