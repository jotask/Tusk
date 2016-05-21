package com.github.jotask.tusk.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.Tusk;

import static com.github.jotask.tusk.util.Constants.*;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();

		cfg.title = TITLE + " - " + VERSION;
		cfg.width = APP_WIDTH;
		cfg.height = APP_HEIGHT;
		cfg.foregroundFPS = FOREGROUND_FPS;
		cfg.backgroundFPS = BACKGROUND_FPS;

		cfg.resizable = false;
		cfg.initialBackgroundColor = Color.WHITE;

		new LwjglApplication(new Tusk(), cfg);
	}
}
