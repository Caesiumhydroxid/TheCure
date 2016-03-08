package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public abstract class Action {
	protected World world;
	public boolean toDelete=false;
	public Action(World world)
	{
		this.world = world;
	}
	public abstract void run();
	public void update(float delta){
		
	}
}
