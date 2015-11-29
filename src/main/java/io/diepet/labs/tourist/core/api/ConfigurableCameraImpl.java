package io.diepet.labs.tourist.core.api;

final class ConfigurableCameraImpl implements ConfigurableCamera {

	private CameraRoll cameraRoll;

	ConfigurableCameraImpl() {
		super();
	}

	@Override
	public boolean shot(String picture) {
		boolean result = false;
		if (cameraRoll != null) {
			result = cameraRoll.addShot(new ShotImpl(picture));
		}
		return result;
	}

	@Override
	public CameraRoll replaceCameraRoll(CameraRoll cameraRoll) {
		CameraRoll previousCameraRoll = this.cameraRoll;
		this.cameraRoll = cameraRoll;
		return previousCameraRoll;
	}

	@Override
	public boolean isOn() {
		return this.cameraRoll != null;
	}
}
