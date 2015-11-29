package io.diepet.labs.tourist.core.api;

final public class CameraRollFactoryImpl implements CameraRollFactory {

	@Override
	public CameraRoll createNewInstance() {
		return new CameraRollImpl();
	}

}
