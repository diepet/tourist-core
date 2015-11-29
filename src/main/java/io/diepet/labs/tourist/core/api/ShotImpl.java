package io.diepet.labs.tourist.core.api;

final class ShotImpl implements Shot {

	private String picture;
	private long timestamp;

	ShotImpl(String picture) {
		this.picture = picture;
		this.timestamp = System.currentTimeMillis();
	}

	public String getPicture() {
		return picture;
	}

	public long getTimestamp() {
		return timestamp;
	}
}
