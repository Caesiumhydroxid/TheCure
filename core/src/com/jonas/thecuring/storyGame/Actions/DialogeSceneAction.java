package com.jonas.thecuring.storyGame.Actions;

import com.badlogic.gdx.math.Vector2;
import com.jonas.thecuring.storyGame.World;

public class DialogeSceneAction extends Action {
	
	String[] messages;
	float[] durations;
	float[] positions;
	public DialogeSceneAction(World world, String[] messages, float[] durations, Action nextAction) {
		super(world, nextAction);
		this.messages = messages;
		this.durations = durations;
	}
	public DialogeSceneAction(World world, String[] messages, float[] durations,float[] positions, Action nextAction) {
		super(world, nextAction);
		this.messages = messages;
		this.durations = durations;
		this.positions = positions;
	}
	@Override
	public void run() {
		running = true;
		Action a= nextAction;
		for(int i= messages.length-1;i>=0;i--)
		{
			if(positions!=null)
				a = new DisplayDialogueAction(world, messages[i],durations[i],positions[i], new TimerAction(world, durations[i], a));
			else
				a = new DisplayDialogueAction(world, messages[i],durations[i], new TimerAction(world, durations[i], a));
		}
		a.run();
	}

}
