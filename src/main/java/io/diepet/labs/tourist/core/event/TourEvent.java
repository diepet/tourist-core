package io.diepet.labs.tourist.core.event;

import io.diepet.labs.tourist.core.api.Tour;

/**
 * The Class TourEvent.
 */
final public class TourEvent {

	/** The tour event type. */
	private TourEventType tourEventType;
	
	/** The tour. */
	private Tour tour;

	/**
	 * Instantiates a new tour event.
	 *
	 * @param tourEventType
	 *            the tour event type
	 * @param tour
	 *            the tour
	 */
	public TourEvent(TourEventType tourEventType, Tour tour) {
		this.tourEventType = tourEventType;
		this.tour = tour;
	}

	/**
	 * Gets the tour event type.
	 *
	 * @return the tour event type
	 */
	public TourEventType getTourEventType() {
		return tourEventType;
	}

	/**
	 * Gets the tour.
	 *
	 * @return the tour
	 */
	public Tour getTour() {
		return tour;
	}
}
