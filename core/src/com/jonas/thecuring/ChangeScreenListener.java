package com.jonas.thecuring;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ChangeScreenListener extends ChangeListener
{
	private ScreenEnum screen;
	private InputMultiplexer multiplexer;
	private InputProcessor processor;
	ChangeScreenListener(ScreenEnum newScreen,InputMultiplexer multiplexer,InputProcessor processor)
	{
		this.screen = newScreen;
		this.multiplexer = multiplexer;
		this.processor = processor;
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		this.multiplexer.removeProcessor(processor);
		ScreenManager.getInstance().show(screen,multiplexer);
	}	
}