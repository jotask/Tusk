package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.util.Constants;

public class AbstractState implements IState{

    protected Color bgColor = Color.CYAN;
    protected Camera camera;

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth() / Constants.SCALE,
                Gdx.graphics.getHeight() / Constants.SCALE);
    }

    @Override
    public void preUpdate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void postUpdate() {

    }

    @Override
    public void preRender(SpriteBatch sb) {

    }

    @Override
    public void render(SpriteBatch sb) {
        
    }

    @Override
    public void postRender(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    @Override
    public void dispose() {}

    public Color getBgColor() {
        return bgColor;
    }

    public Camera getCamera() { return camera; }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

}
