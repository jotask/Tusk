package com.github.jotask.tusk.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.IState;

/**
 * AbstractGameState
 *
 * @author Jose Vives Iznardo
 * @since 18/06/2016
 */
public class AbstractGameState implements IState{

    protected final Play play;

    protected AbstractGameState(final Play play){ this.play = play; }

    @Override
    public void init() { }

    @Override
    public void preUpdate() { }

    @Override
    public void update() { }

    @Override
    public void postUpdate() { }

    @Override
    public void preRender(SpriteBatch sb) { }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void postRender(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) { }

    @Override
    public void dispose() { }

}
