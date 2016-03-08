package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.jonas.thecuring.storyGame.World;

public class DisplayActionText extends Action {

	String text;
	
	public DisplayActionText(World world,String text,Action triggeredAction) {
		super(world,null);
		this.nextAction = triggeredAction;
		this.text = text;
	}

	@Override
	public void run() {
		world.player.setText(text);
		if(Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			nextAction.run();
		}
	}
}
