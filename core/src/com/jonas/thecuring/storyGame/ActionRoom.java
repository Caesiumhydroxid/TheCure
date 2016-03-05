package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.math.Rectangle;

public class ActionRoom extends Rectangle{
	
	private static final long serialVersionUID = -4717073924160149984L;

	private Runnable event;
	ActionRoom()
	{
	}
	ActionRoom(Runnable event)
	{
		this.event = event;
	}
	ActionRoom(Rectangle rect,Runnable event)
	{
		super(rect);
		this.event = event;
	}
	public void triggerEvent()
	{
		if(event!=null)
			event.run();
	}
}
