package com.github.jotask.tusk.engine.loader;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.play.AbstractGameState;
import com.github.jotask.tusk.play.Play;

/**
 * LoadGame
 *
 * @author Jose Vives Iznardo
 * @since 17/06/2016
 */
public class LoadGame extends AbstractGameState {

    private final AbstractGameState stateToLoad;

    private LoadingBar bar;

    public LoadGame(Play play, AbstractGameState stateToLoad) {
        super(play);
        this.stateToLoad = stateToLoad;
    }

    @Override
    public void init() { this.bar = new LoadingBar(play.getCamera()); }

    @Override
    public void update() { play.getManager().finishLoading(stateToLoad); }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) {
        this.bar.render(sr);
    }

    @Override
    public void dispose() { }
}
