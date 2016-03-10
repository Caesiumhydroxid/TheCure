package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SetPlayerAction extends Action {

	private boolean visible;
	private boolean processingInput;
	public SetPlayerAction(World world, boolean visible,boolean processingInput,Action nextAction) {
		super(world, nextAction);
		this.visible = visible;
		this.processingInput = processingInput;
	}

	@Override
	public void run() {
		world.player.render = visible;
		world.player.processInput = processingInput;
		running = true;
		toDelete = true;
		if(nextAction!=null)
		{
			new AddActionToRoom(world, nextAction).run();
		}
	}

}
