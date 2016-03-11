package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.World;

public class AnimationNPCAction extends Action {

	NPC npc;
	Animation animation;
	public AnimationNPCAction(World world,NPC npc,Animation animation, Action nextAction) {
		super(world, nextAction);
		this.animation = animation;
		this.npc = npc;
	}

	@Override
	public void run() {
		running = true;
		npc.setAnimation(animation);
		toDelete = true;
		if(nextAction!= null)
		{
			nextAction.run();
		}
	}

}
