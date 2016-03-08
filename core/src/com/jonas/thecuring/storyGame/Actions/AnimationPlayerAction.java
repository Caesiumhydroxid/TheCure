package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.jonas.thecuring.storyGame.World;

public class AnimationPlayerAction extends Action{
	private boolean running = false;
	private Animation animation;
	private Action actionWhenFinished;
	public AnimationPlayerAction(World world,Animation animation,Action actionWhenFinished) {
		super(world);
		this.animation = animation;
		this.actionWhenFinished = actionWhenFinished;
	}
	
	@Override
	public void update(float delta) {
		if(running)
		{
			if(world.player.currentAnimation.isAnimationFinished(world.player.elapsedTime))
			{
				toDelete = true;
				world.player.allowedToChangeAnimations = true;
				world.player.processInput = true;
				actionWhenFinished.run();
			}
			super.update(delta);
		}
	}

	@Override
	public void run() {
		if(!running)
		{
			world.player.currentAnimation = animation;
			world.player.allowedToChangeAnimations = false;
			world.player.elapsedTime = 0;
		}
		running = true;
	}
	
}
