package com.jonas.thecuring.storyGame;

import com.badlogic.gdx.math.Rectangle;
import com.jonas.thecuring.storyGame.Actions.Action;

public class ActionRoom extends Rectangle{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1750815793712483322L;
	private Action action;
	ActionRoom()
	{
		super();
	}
	ActionRoom(Action action)
	{
		super();
		this.action = action;
	}
	public ActionRoom(Rectangle rect,Action action)
	{
		super(rect);
		this.action = action;
	}
	public void setRectangle(Rectangle rectangle)
	{
		 Rectangle rect = this;
		 rect = rectangle;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Action getAction() {
		return action;
	}
	public void update(float delta)
	{
		if(action!= null)
			action.update(delta);
	}
	public void triggerEvent()
	{
		if(action!=null)
			action.run();
	}
}
