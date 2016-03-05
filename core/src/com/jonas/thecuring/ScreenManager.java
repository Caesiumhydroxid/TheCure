package com.jonas.thecuring;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenManager {
	private Game game;
	private static ScreenManager instance;

	private ScreenManager() {
		super();
	}

	public static ScreenManager getInstance() {
		if (instance == null) {
			instance = new ScreenManager();
		}
		return instance;
	}
	
	public void init(Game game)
	{
		this.game = game;
	}
	
	public void show(ScreenEnum screenEnum,Object... params) {
		// Get current screen to dispose it
        Screen currentScreen = game.getScreen();
 
        // Show new screen
        Screen newScreen = screenEnum.getScreen(params);
        game.setScreen(newScreen);
 
        // Dispose previous screen
        if (currentScreen != null) {
            currentScreen.dispose();
        }
	}
}
