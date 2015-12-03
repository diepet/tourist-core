package io.diepet.labs.tourist.core.api;

/**
 * The default implementation of {@link CameraRollFactory}.
 */
final public class CameraRollFactoryImpl implements CameraRollFactory {

	/* (non-Javadoc)
	 * @see io.diepet.labs.tourist.core.api.CameraRollFactory#createNewInstance()
	 */
	@Override
	public CameraRoll createNewInstance() {
		return new CameraRollImpl();
	}

}
