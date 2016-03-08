package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class NextDayAction extends Action {
	
	private Action followupAction;
	public NextDayAction(World world,Action followupAction) {
		super(world);
		this.followupAction = followupAction;
	}

	@Override
	public void run() {
		world.day++;
		followupAction.run();
	}

}
