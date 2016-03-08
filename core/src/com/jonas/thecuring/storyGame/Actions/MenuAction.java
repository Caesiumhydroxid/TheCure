package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class MenuAction extends Action {

	private String[] options;
	private Action[] actions;
	public MenuAction(World world,String[] options,Action[] actions) {
		super(world);
		this.options = options;
		this.actions = actions;
	}

	@Override
	public void run() {
		world.displayOptions(options, actions);
	}

}
