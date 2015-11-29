package io.diepet.labs.tourist.core.api;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class EditableCameraRollImpl implements EditableCameraRoll {

	private List<Shot> shotList = new LinkedList<Shot>();

	EditableCameraRollImpl() {
		super();
	}

	@Override
	public List<Shot> getShotList() {
		return Collections.unmodifiableList(shotList);
	}

	@Override
	public boolean addShot(Shot shot) {
		return this.shotList.add(shot);
	}
}
