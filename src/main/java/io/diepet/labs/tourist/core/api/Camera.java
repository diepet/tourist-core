package io.diepet.labs.tourist.core.api;

/**
 * The {@link Tourist}'s Camera interface.
 */
public interface Camera {

	/**
	 * Checks if camera is on.
	 *
	 * @return true, if camera is on
	 */
	boolean isOn();

	/**
	 * Shot a picture (and save it to current camera roll).
	 *
	 * @param picture
	 *            the picture
	 * @return true, if successful
	 */
	boolean shot(String picture);

}
