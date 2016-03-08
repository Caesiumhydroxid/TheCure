package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SetBlackAction extends Action {

	private boolean black;
	public SetBlackAction(World world,boolean black) {
		this(world,black,null);
	}
	public SetBlackAction(World world,boolean black,Action nextAction) {
		super(world, nextAction);
		this.black = black;
	}

	@Override
	public void run() {
		world.blackedOut = black;
		if(nextAction!= null)
			nextAction.run();
	}

}
