package io.diepet.labs.tourist.core.event;

import io.diepet.labs.tourist.core.api.Tour;

final public class TourEvent {

	private TourEventType tourEventType;
	private Tour tour;

	public TourEvent(TourEventType tourEventType, Tour tour) {
		this.tourEventType = tourEventType;
		this.tour = tour;
	}

	public TourEventType getTourEventType() {
		return tourEventType;
	}

	public Tour getTour() {
		return tour;
	}
}
