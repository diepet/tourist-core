package io.diepet.labs.tourist.core.api;

import io.diepet.labs.tourist.core.event.TourEventType;

/**
 * The Lockable interface. If a {@link CameraRoll} implementation implements
 * this interface, the {@link #lock()} method will be called in order to lock a
 * {@link CameraRoll} before launching the {@link TourEventType#TOUR_ENDED}
 * event.
 */
interface Lockable {

	/**
	 * Lock.
	 */
	void lock();

}
