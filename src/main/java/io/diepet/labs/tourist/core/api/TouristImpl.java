package io.diepet.labs.tourist.core.api;

import java.util.Set;
import java.util.Stack;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.event.TourEvent;
import io.diepet.labs.tourist.core.event.TourEventListener;
import io.diepet.labs.tourist.core.event.TourEventType;

public class TouristImpl implements Tourist {

	/* configurable fields */
	private Set<TourEventListener> tourEventListenerSet;
	private CameraRollFactory cameraRollFactory;

	/* internal (not configurable) fields */
	private ThreadLocal<Stack<Tour>> threadLocalTourStack = new ThreadLocal<Stack<Tour>>();
	private ThreadLocal<ConfigurableCamera> threadLocalCamera = new ThreadLocal<ConfigurableCamera>();

	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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

	@Override
	public Camera getCamera() {
		return getThreadLocalCamera();
	}

	private void fireTourEvent(TourEvent tourEvent) {
		for (TourEventListener tourEventListener : tourEventListenerSet) {
			tourEventListener.onTourEvent(tourEvent);
		}
	}

	private Stack<Tour> getThreadLocalTourStack() {
		Stack<Tour> stack = threadLocalTourStack.get();
		if (stack == null) {
			stack = new Stack<Tour>();
			threadLocalTourStack.set(stack);
		}
		return stack;
	}

	private ConfigurableCamera getThreadLocalCamera() {
		ConfigurableCamera camera = threadLocalCamera.get();
		if (camera == null) {
			camera = new ConfigurableCameraImpl();
			threadLocalCamera.set(camera);
		}
		return camera;
	}

	private void lockCameraRoll(CameraRoll cameraRoll) {
		if (cameraRoll instanceof Lockable) {
			((Lockable) cameraRoll).lock();
		}
	}

	public void setTourEventListenerSet(Set<TourEventListener> tourEventListenerSet) {
		this.tourEventListenerSet = tourEventListenerSet;
	}

	public void setCameraRollFactory(CameraRollFactory cameraRollFactory) {
		this.cameraRollFactory = cameraRollFactory;
	}

}
