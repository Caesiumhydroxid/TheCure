package com.jonas.thecuring.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jonas.thecuring.TheCuring;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = (180*4);
		config.width = (320*4);
		//config.height = 1080;
		//config.width = 1920;
		config.resizable = true;
		config.vSyncEnabled = true;
		config.fullscreen = false;
		new LwjglApplication(new TheCuring(), config);
	}
}
