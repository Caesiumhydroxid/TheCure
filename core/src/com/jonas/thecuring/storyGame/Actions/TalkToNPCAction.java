package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.NPC;
import com.jonas.thecuring.storyGame.World;

public class TalkToNPCAction extends Action {

	NPC npc;
	public TalkToNPCAction(World world,NPC npc, Action nextAction) {
		super(world, nextAction);
		this.npc = npc;
	}

	@Override
	public void run() {
		new DisplayDialogueAction(world, npc.getNextMessage(), nextAction).run();
		running = true;
	}

}
