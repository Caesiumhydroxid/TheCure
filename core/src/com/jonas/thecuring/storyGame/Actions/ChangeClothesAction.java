package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.TransitionListener;
import com.jonas.thecuring.storyGame.TransitionScreen;
import com.jonas.thecuring.storyGame.World;

public class ChangeClothesAction extends Action
{
	public ChangeClothesAction(World world)
	{
		super(world);
	}

	@Override
	public void run() {
		TransitionScreen transition = new TransitionScreen(1);
		transition.addListener(new TransitionListener() {	
			@Override
			public void transitionIsAtMax() {
				world.player.dress();
			}
			
			@Override
			public void transitionFinished() {
			}
		});
		world.push(transition);
	}
};