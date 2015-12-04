package io.tourist.core.event;

/**
 * The listener interface for receiving {@link TourEvent} events.
 *
 * @see TourEventEvent
 */
public interface TourEventListener {

	/**
	 * On tour event.
	 *
	 * @param tourEvent
	 *            the tour event
	 */
	void onTourEvent(TourEvent tourEvent);

}
