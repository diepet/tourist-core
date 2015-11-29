package io.diepet.labs.tourist.core.api;

class ConfigurableCameraImpl implements ConfigurableCamera {

	private EditableCameraRoll editableCameraRoll;

	ConfigurableCameraImpl() {
		super();
	}

	@Override
	public boolean shot(String picture) {
		boolean result = false;
		if (editableCameraRoll != null) {
			result = editableCameraRoll.addShot(new Shot(picture));
		}
		return result;
	}

	@Override
	public EditableCameraRoll replaceEditableCameraRoll(EditableCameraRoll editableCameraRoll) {
		EditableCameraRoll previousEditableCameraRoll = this.editableCameraRoll;
		this.editableCameraRoll = editableCameraRoll;
		return previousEditableCameraRoll;
	}

	@Override
	public boolean isOn() {
		return this.editableCameraRoll != null;
	}
}
