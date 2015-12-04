package io.tourist.core.api;

/**
 * The ConfigurableCamera interface: adds methods to change a {@link Camera}
 * configuration.
 */
interface ConfigurableCamera extends Camera {

	/**
	 * Replace camera roll.
	 *
	 * @param cameraRoll
	 *            the camera roll
	 * @return the camera roll
	 */
	CameraRoll replaceCameraRoll(CameraRoll cameraRoll);
}
