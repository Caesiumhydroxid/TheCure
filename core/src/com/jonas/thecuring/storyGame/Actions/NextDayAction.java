package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class NextDayAction extends Action {
	
	public NextDayAction(World world,Action nextAction) {
		super(world,nextAction);
	}

	@Override
	public void run() {
		world.day++;
		nextAction.run();
	}

}
