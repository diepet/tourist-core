package io.diepet.labs.tourist.core.event;

import java.io.IOException;
import java.io.OutputStream;

import io.diepet.labs.tourist.core.api.CameraRoll;
import io.diepet.labs.tourist.core.api.Shot;
import io.diepet.labs.tourist.core.api.Tour;

public class ShotPrinterTourEventListener extends TourEventListenerAdapter {

	public static final String TRAVEL_START_TAG_FORMAT = "-- START TRAVEL --%n";
	public static final String TRAVEL_END_TAG_FORMAT = "-- END TRAVEL --%n";
	public static final String TAB = "\t";
	public static final String METHOD_FORMAT = "%s():%n";
	public static final String SHOT_FORMAT = "SHOT #%d: %s%n";
	public static final String EXCEPTION_THROWN_FORMAT = "EXCEPTION THROWN: %s%n";

	final private OutputStream outputStream;

	final private ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>();

	final private ThreadLocal<Integer> threadLocalStackLevel = new ThreadLocal<Integer>();

	public ShotPrinterTourEventListener(final OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	@Override
	public void onTouristTravelStarted(final Tour tour) {
		final StringBuilder buffer = new StringBuilder();
		this.threadLocalStringBuilder.set(buffer);
		this.threadLocalStackLevel.set(Integer.valueOf(0));
		buffer.append(String.format(TRAVEL_START_TAG_FORMAT));
	}

	@Override
	public void onTourStarted(final Tour tour) {
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		final int stackLevel = this.threadLocalStackLevel.get().intValue();
		final String methodName = tour.getProceedingJoinPoint().getSignature().getName();
		repeatString(buffer, TAB, stackLevel);
		buffer.append(String.format(METHOD_FORMAT, methodName));
		this.threadLocalStackLevel.set(Integer.valueOf(stackLevel + 1));
	}

	@Override
	public void onTourEnded(final Tour tour) {
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		final int stackLevel = this.threadLocalStackLevel.get().intValue();
		final CameraRoll cameraRoll = tour.getCameraRoll();
		int i = 1;
		for (Shot shot : cameraRoll.getShotList()) {
			repeatString(buffer, TAB, stackLevel);
			buffer.append(String.format(SHOT_FORMAT, i++, shot.getPicture()));
		}
		this.threadLocalStackLevel.set(Integer.valueOf(stackLevel - 1));
	}

	@Override
	public void onTourFailed(Tour tour) {
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		final int stackLevel = this.threadLocalStackLevel.get().intValue();
		repeatString(buffer, TAB, stackLevel);
		buffer.append(String.format(EXCEPTION_THROWN_FORMAT, tour.getFailCause().getClass().getName()));
		this.threadLocalStackLevel.set(Integer.valueOf(stackLevel - 1));
	}

	@Override
	public void onTouristTravelEnded(Tour tour) {
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		this.threadLocalStringBuilder.remove();
		this.threadLocalStackLevel.remove();
		buffer.append(String.format(TRAVEL_END_TAG_FORMAT));
		flushBufferToOutputStream(buffer);
	}

	synchronized private void flushBufferToOutputStream(final StringBuilder buffer) {
		try {
			outputStream.write(buffer.toString().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			// do nothing
		}
	}

	private void repeatString(final StringBuilder buffer, String s, int n) {
		for (int i = 0; i < n; i++) {
			buffer.append(s);
		}
	}

}
