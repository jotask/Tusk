package com.github.jotask.tusk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Tusk extends Game {

	public enum STATE {SPLASH, MENU, OPTIONS, PLAY, EXIT}

	@Override
	public void create () {
		this.changeState(STATE.SPLASH);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	public void changeState(STATE state){
		Screen screen = null;
		switch (state){
			case EXIT:
				break;
			case PLAY:
				break;
			case OPTIONS:
				break;
			case MENU:
				break;
			default:
			case SPLASH:
				screen = new Splash();
				break;
		}
		this.setScreen(screen);
	}

}
