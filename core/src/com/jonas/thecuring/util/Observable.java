package com.jonas.thecuring.util;

import java.util.ArrayList;
import java.util.List;

public class Observable {
	private List<Observer> observers;
	boolean changed;
	public synchronized void setChanged()
	{
		changed=true;
	}
	public synchronized void notifyObservers(Object arg1)
	{
		for(Observer o:observers)
		{
			o.update(this, arg1);
		}
	}
	
	public synchronized void notifyObservers()
	{
		for(Observer o:observers)
		{
			o.update(this, null);
		}
	}
	public synchronized void deleteObservers() {
		getObservers().clear();
	}

	public synchronized void addObserver(Observer o) {
		getObservers().add(o);
	}

	public synchronized int countObservers() {
		return getObservers().size();
	}

	public synchronized void deleteObserver(Observer o) {
		getObservers().remove(o);
	}

	private List<Observer> getObservers() {
		if (observers == null)
			observers = new ArrayList<Observer>();
		return observers;
	}
}
