package io.diepet.labs.tourist.core.api;

import java.util.List;

public interface CameraRoll {

	List<Shot> getShotList();

	boolean addShot(Shot shot);

}
