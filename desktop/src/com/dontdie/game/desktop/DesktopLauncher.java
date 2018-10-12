package com.dontdie.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dontdie.game.DontDie;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DontDie.WIDTH;
		config.height = DontDie.HEIGHT;
		config.title = DontDie.TITLE;
		new LwjglApplication(new DontDie(), config);
	}
}