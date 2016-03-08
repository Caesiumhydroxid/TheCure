package com.jonas.thecuring.storyGame.Actions;



import com.jonas.thecuring.storyGame.World;

public class SwitchWithComparatorAction extends Action{
	
	Action actionTrue;
	Action actionFalse;
	CustomComparator comparator;
	public <T> SwitchWithComparatorAction(World world,CustomComparator comparator,Action actionTrue,Action actionFalse) {
		super(world,null);
		this.comparator = comparator;
		this.actionTrue = actionTrue;
		this.actionFalse = actionFalse;
	}

	@Override
	public void run() {
		
		if(comparator.compare())
		{
			actionTrue.run();
		}
		else
			actionFalse.run();
		
	}
}
