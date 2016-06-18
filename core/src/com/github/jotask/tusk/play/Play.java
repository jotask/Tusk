package com.github.jotask.tusk.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.states.AbstractState;

/**
 * Play
 *
 * @author Jose Vives Iznardo
 * @since 18/06/2016
 */
public final class Play extends AbstractState {

    private GameManager manager;

    public Play(final Tusk tusk) { super(tusk); }

    @Override
    public void init() {
        this.manager = new GameManager(this);
        this.manager.changeState(GameManager.Game.GAME);
        this.setBgColor(Color.BLACK);
    }

    @Override
    public void update() { this.manager.update(); }

    @Override
    public void render(SpriteBatch sb) { this.manager.render(sb); }

    @Override
    public void debug(ShapeRenderer sr) { this.manager.debug(sr); }

    @Override
    public void dispose() { this.manager.dispose(); }

    public GameManager getManager() { return manager; }
}
