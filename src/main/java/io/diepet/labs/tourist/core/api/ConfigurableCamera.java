package io.diepet.labs.tourist.core.api;

interface ConfigurableCamera extends Camera {
	EditableCameraRoll replaceEditableCameraRoll(EditableCameraRoll editableCameraRoll);
}
