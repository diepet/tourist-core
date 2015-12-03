package io.diepet.labs.tourist.core.event;

import io.diepet.labs.tourist.core.api.Tour;

/**
 * An adapter of {@link TourEventListener}. A
 * {@link TourEventType#TOURIST_TRAVEL_STARTED} event will trigger the
 * {@link #onTouristTravelStarted(Tour)} method.
 * {@link TourEventType#TOUR_STARTED} event will trigger the
 * {@link #onTourStarted(Tour)} method. {@link TourEventType#TOUR_ENDED} event
 * will trigger the {@link #onTourEnded(Tour)} method.
 * {@link TourEventType#TOUR_FAILED} event will trigger the
 * {@link #onTourFailed(Tour)} method.
 * {@link TourEventType#TOURIST_TRAVEL_ENDED} event will trigger the
 * {@link #onTouristTravelStarted(Tour)} method.
 */
abstract public class TourEventListenerAdapter implements TourEventListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.event.TourEventListener#onTourEvent(io.diepet
	 * .labs.tourist.core.event.TourEvent)
	 */
	@Override
	final public void onTourEvent(TourEvent tourEvent) {
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

	/**
	 * On tourist travel started.
	 *
	 * @param tour
	 *            the tour
	 */
	public void onTouristTravelStarted(Tour tour) {
		// empty implementation
	}

	/**
	 * On tour started.
	 *
	 * @param tour
	 *            the tour
	 */
	public void onTourStarted(Tour tour) {
		// empty implementation
	}

	/**
	 * On tour ended.
	 *
	 * @param tour
	 *            the tour
	 */
	public void onTourEnded(Tour tour) {
		// empty implementation
	}

	/**
	 * On tourist travel ended.
	 *
	 * @param tour
	 *            the tour
	 */
	public void onTouristTravelEnded(Tour tour) {
		// empty implementation
	}

	/**
	 * On tour failed.
	 *
	 * @param tour
	 *            the tour
	 */
	public void onTourFailed(Tour tour) {
		// empty implementation
	}
}
