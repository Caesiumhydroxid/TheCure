package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public abstract class WorldComperator implements CustomComparator {

	public WorldComperator(World world)
	{
		this.world = world;
	}
	protected World world;
	@Override
	public abstract boolean compare();

}
