package io.tourist.core.event;

import io.tourist.core.api.Tour;

/**
 * The TourEvent class containing a tour event data.
 */
public final class TourEvent {

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
	public TourEvent(final TourEventType tourEventType, final Tour tour) {
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
