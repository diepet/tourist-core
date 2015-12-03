package io.diepet.labs.tourist.core.event;

import java.io.IOException;
import java.io.OutputStream;

import io.diepet.labs.tourist.core.api.CameraRoll;
import io.diepet.labs.tourist.core.api.Shot;
import io.diepet.labs.tourist.core.api.Tour;

/**
 * A sample of a {@link TourEventListenerAdapter} instantiation. The tour events
 * will be written in an {@link java.io.OutputStream}.
 */
public final class ShotPrinterTourEventListener extends TourEventListenerAdapter {

	/** The Constant TRAVEL_START_TAG_FORMAT. */
	public static final String TRAVEL_START_TAG_FORMAT = "-- START TRAVEL --%n";

	/** The Constant TRAVEL_END_TAG_FORMAT. */
	public static final String TRAVEL_END_TAG_FORMAT = "-- END TRAVEL --%n";

	/** The Constant TAB. */
	public static final String TAB = "\t";

	/** The Constant METHOD_FORMAT. */
	public static final String METHOD_FORMAT = "%s():%n";

	/** The Constant SHOT_FORMAT. */
	public static final String SHOT_FORMAT = "SHOT #%d: %s%n";

	/** The Constant EXCEPTION_THROWN_FORMAT. */
	public static final String EXCEPTION_THROWN_FORMAT = "EXCEPTION THROWN: %s%n";

	/** The output stream. */
	private final OutputStream outputStream;

	/** The thread local string builder. */
	private final ThreadLocal<StringBuilder> threadLocalStringBuilder = new ThreadLocal<StringBuilder>();

	/** The thread local stack level. */
	private final ThreadLocal<Integer> threadLocalStackLevel = new ThreadLocal<Integer>();

	/**
	 * Instantiates a new shot printer tour event listener.
	 *
	 * @param outputStream
	 *            the output stream
	 */
	public ShotPrinterTourEventListener(final OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.event.TourEventListenerAdapter#
	 * onTouristTravelStarted(io.diepet.labs.tourist.core.api.Tour)
	 */
	@Override
	public void onTouristTravelStarted(final Tour tour) {
		super.onTouristTravelStarted(tour);
		final StringBuilder buffer = new StringBuilder();
		this.threadLocalStringBuilder.set(buffer);
		this.threadLocalStackLevel.set(Integer.valueOf(0));
		buffer.append(String.format(TRAVEL_START_TAG_FORMAT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.event.TourEventListenerAdapter#onTourStarted(
	 * io.diepet.labs.tourist.core.api.Tour)
	 */
	@Override
	public void onTourStarted(final Tour tour) {
		super.onTourStarted(tour);
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		final int stackLevel = this.threadLocalStackLevel.get().intValue();
		final String methodName = tour.getProceedingJoinPoint().getSignature().getName();
		repeatString(buffer, TAB, stackLevel);
		buffer.append(String.format(METHOD_FORMAT, methodName));
		this.threadLocalStackLevel.set(Integer.valueOf(stackLevel + 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.event.TourEventListenerAdapter#onTourEnded(io
	 * .diepet.labs.tourist.core.api.Tour)
	 */
	@Override
	public void onTourEnded(final Tour tour) {
		super.onTourEnded(tour);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * io.diepet.labs.tourist.core.event.TourEventListenerAdapter#onTourFailed(
	 * io.diepet.labs.tourist.core.api.Tour)
	 */
	@Override
	public void onTourFailed(final Tour tour) {
		super.onTourFailed(tour);
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		final int stackLevel = this.threadLocalStackLevel.get().intValue();
		repeatString(buffer, TAB, stackLevel);
		buffer.append(String.format(EXCEPTION_THROWN_FORMAT, tour.getFailCause().getClass().getName()));
		this.threadLocalStackLevel.set(Integer.valueOf(stackLevel - 1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.diepet.labs.tourist.core.event.TourEventListenerAdapter#
	 * onTouristTravelEnded(io.diepet.labs.tourist.core.api.Tour)
	 */
	@Override
	public void onTouristTravelEnded(final Tour tour) {
		super.onTouristTravelEnded(tour);
		final StringBuilder buffer = this.threadLocalStringBuilder.get();
		this.threadLocalStringBuilder.remove();
		this.threadLocalStackLevel.remove();
		buffer.append(String.format(TRAVEL_END_TAG_FORMAT));
		flushBufferToOutputStream(buffer);
	}

	/**
	 * Flush buffer to output stream.
	 *
	 * @param buffer
	 *            the buffer
	 */
	private synchronized void flushBufferToOutputStream(final StringBuilder buffer) {
		try {
			outputStream.write(buffer.toString().getBytes());
			outputStream.flush();
		} catch (IOException e) {
			// do nothing
		}
	}

	/**
	 * Repeat string.
	 *
	 * @param buffer
	 *            the buffer where to append the string repeating
	 * @param s
	 *            the string to repeat
	 * @param n
	 *            how many times the string will be repeated
	 */
	private void repeatString(final StringBuilder buffer, final String s, final int n) {
		for (int i = 0; i < n; i++) {
			buffer.append(s);
		}
	}

}
