package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class DisplayTextAction extends Action{
	private String text;
	private boolean continous;
	private boolean showed;
	public DisplayTextAction(String text,World world,boolean contionus)
	{
		
		this.world = world;
		this.text = text;
		this.continous = contionus;
		showed = false;
	}

	@Override
	public void run() {
		if(!showed)
			world.displayText(text);
		if(!continous)
			showed = true;
		
	}
	
}
