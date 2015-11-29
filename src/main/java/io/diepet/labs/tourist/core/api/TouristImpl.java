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
	private EditableCameraRollFactory editableCameraRollFactory;

	/* internal (not configurable) fields */
	private ThreadLocal<Stack<Tour>> threadLocalTourStack = new ThreadLocal<Stack<Tour>>();
	private ThreadLocal<ConfigurableCamera> threadLocalCamera = new ThreadLocal<ConfigurableCamera>();

	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		final Stack<Tour> tourStack = getThreadLocalTourStack();
		final ConfigurableCamera camera = getThreadLocalCamera();
		final EditableCameraRoll cameraRoll = editableCameraRollFactory.createNewInstance();
		final Tour tour = new Tour(proceedingJoinPoint);
		if (tourStack.isEmpty()) {
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_STARTED, tour));
		}
		tourStack.push(tour);
		fireTourEvent(new TourEvent(TourEventType.TOUR_STARTED, tour));
		Object returnObject;
		try {
			// set new camera roll and store the previous camera roll
			final EditableCameraRoll previousCameraRoll = camera.replaceEditableCameraRoll(cameraRoll);
			// join point proceed() call
			returnObject = proceedingJoinPoint.proceed();
			// resume camera roll if changed after proceed()
			camera.replaceEditableCameraRoll(previousCameraRoll);
			tour.setResult(returnObject);
			tour.setCameraRoll(cameraRoll);
			tourStack.pop();
			fireTourEvent(new TourEvent(TourEventType.TOUR_ENDED, tour));
		} catch (Throwable e) {
			tour.setFailCause(e);
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_ENDED, tour));
			throw e;
		}
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

	public void setTourEventListenerSet(Set<TourEventListener> tourEventListenerSet) {
		this.tourEventListenerSet = tourEventListenerSet;
	}

	public void setCameraRollFactory(EditableCameraRollFactory editableCameraRollFactory) {
		this.editableCameraRollFactory = editableCameraRollFactory;
	}

}
