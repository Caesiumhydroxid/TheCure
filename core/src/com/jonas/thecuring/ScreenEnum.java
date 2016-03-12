package com.jonas.thecuring;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.esotericsoftware.kryonet.Server;
import com.jonas.thecuring.cancerGame.CancerGameState;
import com.jonas.thecuring.storyGame.Memory;
import com.jonas.thecuring.storyGame.StoryGameState;

public enum ScreenEnum {
	 
    MAIN_MENU {
        public Screen getScreen(Object... params) {
            return new MenuState((InputMultiplexer)params[0]);
        }
    },
    CONNECT_CLIENT {
        public Screen getScreen(Object... params) {
            return new ConnectingScreenClient((InputMultiplexer)params[0]);
        }
    },
    CONNECT_SERVER {
        public Screen getScreen(Object... params) {
            return new ConnectingScreenServer((InputMultiplexer)params[0]);
        }
    },
	CANCER_GAME{
		public Screen getScreen(Object... params) {
            return new CancerGameState((InputMultiplexer)params[0]);
        }
	},
	STORY_GAME{
		public Screen getScreen(Object... params)
		{
			return new StoryGameState((InputMultiplexer)params[0],(Server) params[1]);
		}
	};
	public Screen getScreen(Object... params) {
		// TODO Auto-generated method stub
		return null;
	}

}