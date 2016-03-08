package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class DisplayDialogueAction extends Action{
	private String text;
	private boolean showed;
	private float timeToShow;
	private float y;
	public DisplayDialogueAction(World world,String text,Action nextAction)
	{
		this(world,text,5,nextAction);
	}
	public DisplayDialogueAction(World world,String text,float timeToShow,Action nextAction)
	{
		super(world,nextAction);
		this.text = text;
		this.timeToShow = timeToShow;
		y = 4;
		showed = false;
	}
	public DisplayDialogueAction(World world,String text,float timeToShow,float y,Action nextAction)
	{
		super(world,nextAction);
		this.text = text;
		this.timeToShow = timeToShow;
		this.y = y;
		showed = false;
	}

	@Override
	public void run() {
		world.displayText(text,timeToShow,y);
		if(nextAction!= null)
		{
			nextAction.run();
		}
		
	}
	
}
