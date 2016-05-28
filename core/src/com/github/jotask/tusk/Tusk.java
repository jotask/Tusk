package com.github.jotask.tusk;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.engine.Debug;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.util.Constants;

public class Tusk extends ApplicationAdapter {

	public static boolean DEBUG = Constants.DEFAULT_DEBUG;

	private GameStateManager gsm;

	private Debug debug;

	private SpriteBatch sb;
	private ShapeRenderer sr;

	@Override
	public void create() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		sr.setAutoShapeType(true);

		AssetManager.get();

		gsm = GameStateManager.get();

		debug = new Debug();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {

		if(Gdx.input.isKeyJustPressed(Input.Keys.Q)){
			DEBUG = !DEBUG;
		}

		gsm.update();
		debug.update();

		Color bgColor = gsm.getState().getBgColor();
		Gdx.gl.glClearColor(bgColor.r, bgColor.g, bgColor.b, bgColor.a);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.render(sb);
		if(this.debug.isEnabled())
			debug.render(sb);

		if(DEBUG) {
			gsm.debug(sr);
			if(this.debug.isEnabled())
				debug.debug(sr);
		}

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		gsm.dispose();
		debug.dispose();
		sb.dispose();
		sr.dispose();
	}
}
