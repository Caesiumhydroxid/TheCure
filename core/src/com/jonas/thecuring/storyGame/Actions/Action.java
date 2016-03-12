package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public abstract class Action {
	protected World world;
	public boolean toDelete=false;
	public boolean running=false;
	protected Action nextAction;
	public Action(World world,Action nextAction)
	{
		this.world = world;
		this.nextAction = nextAction;
	}
	public void init()
	{
		
	}
	public abstract void run();
	public void update(float delta){
	}
}
