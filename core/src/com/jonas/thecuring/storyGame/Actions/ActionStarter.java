package com.jonas.thecuring.storyGame.Actions;

import java.util.ArrayList;
import java.util.Iterator;

import com.jonas.thecuring.storyGame.World;

public class ActionStarter extends Action {
	private ArrayList<Action> actions;
	public ActionStarter(World world, Action nextAction,Action otherNextAction) {
		super(world, nextAction);
		actions = new ArrayList<Action>();
		actions.add(nextAction);
		actions.add(otherNextAction);
	}
	public ActionStarter(World world, ArrayList<Action> actions)
	{
		super(world,null);
		this.actions = actions;
	}
	
	@Override
	public void update(float delta) {
	}
	@Override
	public void run() {
		for(Action action:actions)
		{
			new AddActionToRoom(world, action).run();
		}
	}

}
