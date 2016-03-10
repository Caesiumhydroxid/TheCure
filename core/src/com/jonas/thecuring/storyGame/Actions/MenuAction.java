package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class MenuAction extends Action {

	private String[] options;
	private Action[] actions;
	private boolean big;
	private String question;
	public MenuAction(World world,String[] options,String question,Action[] actions) {
		super(world,null);
		this.options = options;
		this.actions = actions;
		this.question = question;
		big = false;
	}
	public MenuAction(World world, boolean big,String[] options,String question,Action[] actions) {
		super(world,null);
		this.options = options;
		this.actions = actions;
		this.question = question;
		this.big = big;
	}

	@Override
	public void run() {
		world.displayOptions(options, actions,question,big);
	}

}
