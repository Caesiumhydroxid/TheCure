package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SendMessageAction extends Action {
	private String message;
	public SendMessageAction(World world, String message,Action nextAction) {
		super(world, nextAction);
		this.message = message;
	}

	@Override
	public void run() {
		world.sendMessage(message);
		running = true;
		toDelete =true;
	}

}
