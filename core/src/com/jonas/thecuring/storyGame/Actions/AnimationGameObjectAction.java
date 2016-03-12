package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.jonas.thecuring.storyGame.GameObject;
import com.jonas.thecuring.storyGame.World;

public class AnimationGameObjectAction extends Action {

	GameObject obj;
	Animation anim;
	public AnimationGameObjectAction(World world,GameObject obj, Animation anim,Action nextAction) {
		super(world, nextAction);
		this.obj = obj;
		this.anim = anim;
	}

	@Override
	public void run() {
		running = true;
		obj.setCurrentAnimation(anim);
		toDelete = true;
		if(nextAction!= null)
		{
			nextAction.run();
		}

	}

}
