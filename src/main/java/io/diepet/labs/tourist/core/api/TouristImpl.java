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
		Object returnObject;
		CameraRoll previousCameraRoll = null;
		try {
			// set new camera roll and store the previous camera roll
			previousCameraRoll = camera.replaceCameraRoll(cameraRoll);
			// join point proceed() call
			returnObject = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			tour.setFailCause(e);
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour));
			throw e;
		} finally {
			// resume camera roll if changed after proceed()
			camera.replaceCameraRoll(previousCameraRoll);
		}
		// set result and camera roll
		tour.setResult(returnObject);
		tour.setCameraRoll(cameraRoll);
		lockCameraRoll(cameraRoll);
		// pop tour and fire related event
		tourStack.pop();
		fireTourEvent(new TourEvent(TourEventType.TOUR_ENDED, tour));

		if (tourStack.isEmpty()) {
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour));
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
