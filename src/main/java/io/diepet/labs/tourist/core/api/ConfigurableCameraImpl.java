package io.diepet.labs.tourist.core.api;

/**
 * The default implementation of {@link ConfigurableCamera}.
 */
final class ConfigurableCameraImpl implements ConfigurableCamera {

	/** The camera roll. */
	private CameraRoll cameraRoll;

	/**
	 * Instantiates a new configurable camera impl.
	 */
	ConfigurableCameraImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Camera#shot(java.lang.String)
	 */
	@Override
	public boolean shot(final String picture) {
		boolean result = false;
		if (cameraRoll != null) {
			result = cameraRoll.addShot(new ShotImpl(picture));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.api.ConfigurableCamera#replaceCameraRoll(io.
	 * diepet.labs.tourist.core.api.CameraRoll)
	 */
	@Override
	public CameraRoll replaceCameraRoll(final CameraRoll newCameraRoll) {
		CameraRoll previousCameraRoll = this.cameraRoll;
		this.cameraRoll = newCameraRoll;
		return previousCameraRoll;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.Camera#isOn()
	 */
	@Override
	public boolean isOn() {
		return this.cameraRoll != null;
	}
}
