package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SetWorldVariableAction extends Action {

	private VariableSetter setter;
	public SetWorldVariableAction(World world, VariableSetter setter,Action nextAction) {
		super(world, nextAction);
		this.setter = setter;
	}

	@Override
	public void run() {
		running = true;
		setter.setVariable();
		toDelete = true;
		if(nextAction!= null)
			nextAction.run();
	}

}
