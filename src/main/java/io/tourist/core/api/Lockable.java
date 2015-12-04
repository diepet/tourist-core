package io.tourist.core.api;

/**
 * The Lockable interface. If a {@link CameraRoll} implementation implements
 * this interface, the {@link #lock()} method will be called in order to lock a
 * {@link CameraRoll} before launching the
 * {@link io.tourist.core.event.TourEventType#TOUR_ENDED}.
 */
interface Lockable {

	/**
	 * Lock.
	 */
	void lock();

}
