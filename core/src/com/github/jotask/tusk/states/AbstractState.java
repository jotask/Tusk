package com.github.jotask.tusk.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.util.Constants;

public abstract class AbstractState implements IState {

    protected Color bgColor = Color.CYAN;
    protected Camera camera;

    protected final Tusk tusk;

    protected AbstractState(final Tusk tusk) {
        this(tusk, Constants.SCALE);
    }

    protected AbstractState(final Tusk tusk, final float SCALE) {
        this.tusk = tusk;
        this.camera = new Camera(Gdx.graphics.getWidth() / SCALE,
                Gdx.graphics.getHeight() / SCALE);
    }

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

    public Color getBgColor() {
        return bgColor;
    }

    public Camera getCamera() { return camera; }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public Tusk getTusk() { return tusk; }

}
