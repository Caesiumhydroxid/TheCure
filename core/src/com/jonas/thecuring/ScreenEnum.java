package com.jonas.thecuring;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.jonas.thecuring.ui.Styles;

public enum ScreenEnum {
	 
    MAIN_MENU {
        public Screen getScreen(Object... params) {
            return new MenuState((AssetManager)params[0], (Styles)params[1], (InputMultiplexer)params[2]);
        }
    },
    CONNECT {
        public Screen getScreen(Object... params) {
            return new ConnectingScreen((AssetManager)params[0], (Styles)params[1], (InputMultiplexer)params[2],(String)params[3]);
        }
    };

	public Screen getScreen(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}