package io.tourist.core.api;

/**
 * The default implementation of {@link CameraRollFactory}.
 */
public final class CameraRollFactoryImpl implements CameraRollFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.tourist.core.api.CameraRollFactory#createNewInstance()
	 */
	@Override
	public CameraRoll createNewInstance() {
		return new CameraRollImpl();
	}

}
