package io.diepet.labs.tourist.core.impl;

import java.util.Stack;

import org.aspectj.lang.ProceedingJoinPoint;

import io.diepet.labs.tourist.core.api.TouristStack;
import io.diepet.labs.tourist.core.api.AbstractTouristStackItem;
import io.diepet.labs.tourist.core.api.TouristStackItemFactory;

public class TouristStackImpl implements TouristStack {
	
	public ThreadLocal<Stack<AbstractTouristStackItem>> threadLocalStack = new ThreadLocal<Stack<AbstractTouristStackItem>>();
	public TouristStackItemFactory touristStackItemFactory;
	
	public void push(ProceedingJoinPoint proceedingJoinPoint) {
		Stack<AbstractTouristStackItem> stack = threadLocalStack.get();
		if (stack == null) {
			stack = new Stack<AbstractTouristStackItem>();
			threadLocalStack.set(stack);
		}
		stack.push(this.touristStackItemFactory.createNewInstance(proceedingJoinPoint));
	}
	public void pop(Object returnObject) {
		Stack<AbstractTouristStackItem> stack = threadLocalStack.get();
		AbstractTouristStackItem touristStackItem = stack.pop();
		touristStackItem.setReturnObject(returnObject);
	}
	public void exceptionThrown(Throwable e) {
		Stack<AbstractTouristStackItem> stack = threadLocalStack.get();
		stack.clear();
	}
	
	public void setTouristStackItemFactory(TouristStackItemFactory touristStackItemFactory) {
		this.touristStackItemFactory = touristStackItemFactory;
	}
}
