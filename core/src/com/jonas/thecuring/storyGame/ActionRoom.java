package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.math.Rectangle;
import com.jonas.thecuring.storyGame.Actions.Action;

public class ActionRoom extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1750815793712483322L;
	private Action event;
	ActionRoom()
	{
	}
	ActionRoom(Action event)
	{
		this.event = event;
	}
	ActionRoom(Rectangle rect,Action event)
	{
		super(rect);
		this.event = event;
	}
	public void update(float delta)
	{
		event.update(delta);
	}
	public void triggerEvent()
	{
		if(event!=null)
			event.run();
	}
}
