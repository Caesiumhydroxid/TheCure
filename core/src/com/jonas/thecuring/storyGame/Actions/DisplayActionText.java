package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jonas.thecuring.storyGame.World;

public class DisplayActionText extends Action {

	String text;
	Action triggeredAction;
	
	public DisplayActionText(World world,String text,Action triggeredAction) {
		super(world);
		this.triggeredAction = triggeredAction;
		this.text = text;
	}

	@Override
	public void run() {
		world.player.setText(text);
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			triggeredAction.run();
		}
	}
}
