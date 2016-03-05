package com.jonas.thecuring;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class ChangeScreenListener extends ChangeListener
{
	private ScreenEnum screen;
	private Object[] arguments;
	ChangeScreenListener(ScreenEnum newScreen,Object... args)
	{
		this.screen = newScreen;
		this.arguments = args;
	}
	@Override
	public void changed(ChangeEvent event, Actor actor) {
		ScreenManager.getInstance().show(screen,arguments);
	}	
}