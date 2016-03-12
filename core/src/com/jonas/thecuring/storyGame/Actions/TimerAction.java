package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class TimerAction extends Action {
	boolean running=false;
	boolean triggered = false;
	float triggerTime;
	float elapsedTime;
	public TimerAction(World world,float triggerTime, Action nextAction) {
		super(world, nextAction);
		this.triggerTime = triggerTime;
	}
	
	@Override
	public void update(float delta) {
		if(running)
		{
			elapsedTime += delta;
			if(elapsedTime>triggerTime&&!triggered)
			{
				triggered = true;
				if(nextAction != null)
					nextAction.run();
				
				toDelete = true;
			}
		}
		super.update(delta);
	}
	public void init()
	{
		elapsedTime = 0;
		toDelete = false;
		running = false;
	}
	@Override
	public void run() {
		toDelete = false;
		running=true;

	}

}
