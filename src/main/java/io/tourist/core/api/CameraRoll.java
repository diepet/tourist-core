package io.tourist.core.api;

import java.util.List;

/**
 * The CameraRoll interface.
 */
public interface CameraRoll {

	/**
	 * Gets the shot list.
	 *
	 * @return the shot list
	 */
	List<Shot> getShotList();

	/**
	 * Adds the shot.
	 *
	 * @param shot
	 *            the shot
	 * @return true, if successful
	 */
	boolean addShot(Shot shot);

}
