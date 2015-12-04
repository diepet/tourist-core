package io.tourist.core.api;

/**
 * The Class ShotImpl.
 */
final class ShotImpl implements Shot {

	/** The picture. */
	private String picture;

	/** The timestamp. */
	private long timestamp;

	/**
	 * Instantiates a new shot impl.
	 *
	 * @param pictureShot
	 *            the picture
	 */
	ShotImpl(final String pictureShot) {
		this.picture = pictureShot;
		this.timestamp = System.currentTimeMillis();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Shot#getPicture()
	 */
	@Override
	public String getPicture() {
		return picture;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Shot#getTimestamp()
	 */
	@Override
	public long getTimestamp() {
		return timestamp;
	}
}
