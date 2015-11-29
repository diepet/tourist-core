package io.diepet.labs.tourist.core.api;

interface ConfigurableCamera extends Camera {
	CameraRoll replaceCameraRoll(CameraRoll cameraRoll);
}
