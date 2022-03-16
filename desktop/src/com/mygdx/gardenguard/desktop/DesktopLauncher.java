package com.mygdx.gardenguard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.gardenguard.GardenGuard;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GardenGuard.WIDTH;
		config.height = GardenGuard.HEIGHT;
		new LwjglApplication(new GardenGuard(), config);
	}
}
