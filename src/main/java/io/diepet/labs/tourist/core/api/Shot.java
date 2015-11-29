package io.diepet.labs.tourist.core.api;

public class Shot {

	private String picture;
	private long timestamp;

	Shot(String picture) {
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
