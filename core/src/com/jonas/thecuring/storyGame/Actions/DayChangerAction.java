package com.jonas.thecuring.storyGame.Actions;

import java.util.ArrayList;

import com.jonas.thecuring.storyGame.World;

public class DayChangerAction extends Action {

	Action[] actionForDay;
	public DayChangerAction(World world,Action[] actionForDay) {
		super(world,null);
		this.actionForDay = actionForDay;
	}
	public DayChangerAction(World world,ArrayList<Action> actionForDay) {
		super(world,null);
		this.actionForDay = actionForDay.toArray(new Action[actionForDay.size()]);
	}
	@Override
	public void run() {
		actionForDay[world.day].run();
	}
	
}
