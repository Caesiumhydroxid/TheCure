package com.jonas.thecuring.storyGame.Actions;

import com.jonas.thecuring.storyGame.TransitionListener;
import com.jonas.thecuring.storyGame.World;

public class TransitionTextAction extends Action implements TransitionListener {

	private String text;
	private boolean keepBlack;
	private float textTime;
	private float transitionTime;
	public TransitionTextAction(World world, String text,boolean keepBlack,Action nextAction) {
		this(world,2,3,text,keepBlack,nextAction);
	}
	public TransitionTextAction(World world, float transitionTime, float textTime,String text,boolean keepBlack) {
		this(world, text, keepBlack, null);
	}
	public TransitionTextAction(World world, float transitionTime, float textTime,String text,boolean keepBlack,Action nextAction) {
		super(world, nextAction);
		this.keepBlack = keepBlack;
		this.text = text;
		this.transitionTime = transitionTime;
		this.textTime = textTime;
	}
	

	@Override
	public void transitionIsAtMax() {
		new SetBlackAction(world, keepBlack).run();
	}

	@Override
	public void transitionFinished() {
		if(nextAction!= null)
			nextAction.run();
	}

	@Override
	public void run() {
		world.makeTextTransition(transitionTime, textTime, text, this);
	}

}
