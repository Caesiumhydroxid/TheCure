package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public abstract class VariableSetter {
	protected World world;
	public VariableSetter(World world)
	{
		this.world = world;
	}
	public abstract void setVariable();
}
