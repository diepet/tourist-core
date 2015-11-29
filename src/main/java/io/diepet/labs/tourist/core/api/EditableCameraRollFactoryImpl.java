package io.diepet.labs.tourist.core.api;

public class EditableCameraRollFactoryImpl implements EditableCameraRollFactory {

	@Override
	public EditableCameraRoll createNewInstance() {
		return new EditableCameraRollImpl();
	}

}
