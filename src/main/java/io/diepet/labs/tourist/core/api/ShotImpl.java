package io.diepet.labs.tourist.core.api;

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
	 * @param picture
	 *            the picture
	 */
	ShotImpl(String picture) {
		this.picture = picture;
		this.timestamp = System.currentTimeMillis();
	}

	/* (non-Javadoc)
	 * @see io.diepet.labs.tourist.core.api.Shot#getPicture()
	 */
	public String getPicture() {
		return picture;
	}

	/* (non-Javadoc)
	 * @see io.diepet.labs.tourist.core.api.Shot#getTimestamp()
	 */
	public long getTimestamp() {
		return timestamp;
	}
}
