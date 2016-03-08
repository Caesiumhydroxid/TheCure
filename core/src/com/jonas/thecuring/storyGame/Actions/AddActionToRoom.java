package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class AddActionToRoom extends Action{

	public AddActionToRoom(World world, Action action) {
		super(world , action);
	}
	
	
	@Override
	public void update(float delta) {
		
	}


	@Override
	public void run() {
		world.getCurrentRoom().addActionRoom(0, 0, 0, 0, nextAction);
		nextAction.run();
		toDelete = true;
	}

}
