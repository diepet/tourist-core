package io.diepet.labs.tourist.core.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The default implementation of {@link CameraRoll}.
 */
class CameraRollImpl implements CameraRoll {

	/** The shot list. */
	private List<Shot> shotList = new LinkedList<Shot>();

	/**
	 * Instantiates a new camera roll implementation.
	 */
	CameraRollImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.api.CameraRoll#getShotList()
	 */
	@Override
	public List<Shot> getShotList() {
		return Collections.unmodifiableList(shotList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.api.CameraRoll#addShot(io.diepet.labs.tourist
	 * .core.api.Shot)
	 */
	@Override
	public boolean addShot(final Shot shot) {
		return this.shotList.add(shot);
	}
}
