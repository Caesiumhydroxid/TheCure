package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SendMessageIntegerAction extends Action {
	Integer message;
	public SendMessageIntegerAction(World world,Integer message, Action nextAction) {
		super(world, nextAction);
		this.message = message;
	}

	@Override
	public void run() {
		world.sendMessage(message);
		running = true;
		toDelete = true;
		if(nextAction!=null)
			new AddActionToRoom(world, nextAction).run();
	}

}
