package io.diepet.labs.tourist.core.impl;

import java.util.Set;
import java.util.Stack;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.Tour;
import io.diepet.labs.tourist.core.api.TourFactory;
import io.diepet.labs.tourist.core.api.Tourist;
import io.diepet.labs.tourist.core.event.TourEvent;
import io.diepet.labs.tourist.core.event.TourEventListener;
import io.diepet.labs.tourist.core.event.TourEventType;

public class TouristImpl implements Tourist {

	/* configurable fields */
	private TourFactory tourFactory;
	private Set<TourEventListener> tourEventListenerSet;

	/* internal (not configurable) fields */
	private ThreadLocal<Stack<Tour>> threadLocalTourStack = new ThreadLocal<Stack<Tour>>();

	public Object aroundPointcut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		final Stack<Tour> tourStack = getThreadLocalTourStack();
		final Tour tour = tourFactory.createNewInstance(proceedingJoinPoint);
		if (tourStack.isEmpty()) {
			fireTourEvent(new TourEvent(TourEventType.TOURIST_TRAVEL_STARTED, tour));
		}
		tourStack.push(tour);
		fireTourEvent(new TourEvent(TourEventType.TOUR_STARTED, tour));
		Object returnObject;
		try {
			returnObject = proceedingJoinPoint.proceed();
			tour.setResult(returnObject);
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

	public void setTourFactory(TourFactory tourFactory) {
		this.tourFactory = tourFactory;
	}

	public void setTourEventListenerSet(Set<TourEventListener> tourEventListenerSet) {
		this.tourEventListenerSet = tourEventListenerSet;
	}
}
