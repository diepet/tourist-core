package io.tourist.core.api;

import java.util.Set;
import java.util.Stack;

import org.aspectj.lang.ProceedingJoinPoint;

import io.tourist.core.event.TourEvent;
import io.tourist.core.event.TourEventListener;
import io.tourist.core.event.TourEventType;

/**
 * The default implementation of {@link Tourist} interface.
 */
public class TouristImpl implements Tourist {

	/** The tour event listener set. */
	private Set<TourEventListener> tourEventListenerSet;

	/** The camera roll factory. */
	private CameraRollFactory cameraRollFactory;

	/** The thread local tour stack. */
	private ThreadLocal<Stack<Tour>> threadLocalTourStack = new ThreadLocal<Stack<Tour>>();

	/** The thread local camera. */
	private ThreadLocal<ConfigurableCamera> threadLocalCamera = new ThreadLocal<ConfigurableCamera>();

	/**
	 * Around pointcut.
	 *
	 * @param proceedingJoinPoint
	 *            the proceeding join point
	 * @return the object
	 * @throws Throwable
	 *             the throwable
	 */
	public final Object aroundPointcut(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		final Stack<Tour> tourStack = getThreadLocalTourStack();
		final ConfigurableCamera camera = getThreadLocalCamera();
		final CameraRoll cameraRoll = cameraRollFactory.createNewInstance();
		final TourImpl tour = new TourImpl(proceedingJoinPoint);
		if (tourStack.isEmpty()) {
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_STARTED, tour));
		}
		tourStack.push(tour);
		fireTourEvent(new TourEvent(TourEventType.TOUR_STARTED, tour));
		Object returnObject = null;
		// set new camera roll and store the previous camera roll
		CameraRoll previousCameraRoll = camera.replaceCameraRoll(cameraRoll);
		Throwable error = null;
		try {
			// join point proceed() call
			returnObject = proceedingJoinPoint.proceed();
		} catch (Throwable exceptionCaught) {
			error = exceptionCaught;
		} finally {
			// resume camera roll if changed after proceed()
			camera.replaceCameraRoll(previousCameraRoll);
		}

		if (error != null) {
			// if some exception was thrown by proceed()
			// set fail cause
			tour.setFailCause(error);
			fireTourEvent(new TourEvent(TourEventType.TOUR_FAILED, tour));
		} else {
			// if proceed() was completed without exception thrown
			// set result and camera roll
			tour.setResult(returnObject);
			tour.setCameraRoll(cameraRoll);
			lockCameraRoll(cameraRoll);
			fireTourEvent(new TourEvent(TourEventType.TOUR_ENDED, tour));
		}
		// pop tour and fire related event
		tourStack.pop();

		if (tourStack.isEmpty()) {
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour));
		}
		if (error != null) {
			throw error;
		}
		return returnObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see io.tourist.core.api.Tourist#getCamera()
	 */
	@Override
	public final Camera getCamera() {
		return getThreadLocalCamera();
	}

	/**
	 * Fire tour event.
	 *
	 * @param tourEvent
	 *            the tour event
	 */
	private void fireTourEvent(final TourEvent tourEvent) {
		for (TourEventListener tourEventListener : tourEventListenerSet) {
			tourEventListener.onTourEvent(tourEvent);
		}
	}

	/**
	 * Gets the thread local tour stack.
	 *
	 * @return the thread local tour stack
	 */
	private Stack<Tour> getThreadLocalTourStack() {
		Stack<Tour> stack = threadLocalTourStack.get();
		if (stack == null) {
			stack = new Stack<Tour>();
			threadLocalTourStack.set(stack);
		}
		return stack;
	}

	/**
	 * Gets the thread local camera.
	 *
	 * @return the thread local camera
	 */
	private ConfigurableCamera getThreadLocalCamera() {
		ConfigurableCamera camera = threadLocalCamera.get();
		if (camera == null) {
			camera = new ConfigurableCameraImpl();
			threadLocalCamera.set(camera);
		}
		return camera;
	}

	/**
	 * Lock camera roll.
	 *
	 * @param cameraRoll
	 *            the camera roll
	 */
	private void lockCameraRoll(final CameraRoll cameraRoll) {
		if (cameraRoll instanceof Lockable) {
			((Lockable) cameraRoll).lock();
		}
	}

	/**
	 * Sets the tour event listener set.
	 *
	 * @param tourEventListenerSet
	 *            the new tour event listener set
	 */
	public final void setTourEventListenerSet(final Set<TourEventListener> tourEventListenerSet) {
		this.tourEventListenerSet = tourEventListenerSet;
	}

	/**
	 * Sets the camera roll factory.
	 *
	 * @param cameraRollFactory
	 *            the new camera roll factory
	 */
	public final void setCameraRollFactory(final CameraRollFactory cameraRollFactory) {
		this.cameraRollFactory = cameraRollFactory;
	}

}
