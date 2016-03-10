package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class DisplayDialogueEndAction extends Action {

	private String text;
	private float timeToShow;
	private float y;
	public DisplayDialogueEndAction(World world,String text,Action nextAction)
	{
		this(world,text,5,nextAction);
	}
	
	public DisplayDialogueEndAction(World world,String text,float timeToShow,Action nextAction)
	{
		super(world,nextAction);
		this.text = text;
		this.timeToShow = timeToShow;
		y = 4;
	}
	public DisplayDialogueEndAction(World world,String text,float timeToShow,float y,Action nextAction)
	{
		super(world,nextAction);
		this.text = text;
		this.timeToShow = timeToShow;
		this.y = y;
	}

	@Override
	public void run() {
		world.displayText(text,timeToShow,y);
		running = true;
		if(nextAction!= null)
		{
			new AddActionToRoom(world, new TimerAction(world, timeToShow, nextAction)).run();
		}
		toDelete= true;
	}
}
