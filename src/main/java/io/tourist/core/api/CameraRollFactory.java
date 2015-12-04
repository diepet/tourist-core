package io.tourist.core.api;

/**
 * A factory for creating {@link CameraRoll} objects.
 */
public interface CameraRollFactory {

	/**
	 * Creates a new CameraRoll object.
	 *
	 * @return the camera roll
	 */
	CameraRoll createNewInstance();

}
