package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.World;

public class SwitchWithVariableAction extends Action{

	Comparable variable;
	Comparable shouldBe;
	Action actionTrue;
	Action actionFalse;
	public <T> SwitchWithVariableAction(World world,Comparable<T> variable,Comparable<T> shouldBe, Action actionTrue,Action actionFalse) {
		super(world,null);
		this.variable = variable;
		this.actionTrue = actionTrue;
		this.actionFalse = actionFalse;
		this.shouldBe = shouldBe;
	}

	@Override
	public void run() {
		
		if(variable.compareTo(shouldBe)==0)
		{
			actionTrue.run();
		}
		else
			actionFalse.run();
		
	}

}
