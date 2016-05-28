package com.github.jotask.tusk.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface IState{

    void init();

    void preUpdate();
    void update();
    void postUpdate();

    void preRender(SpriteBatch sb);
    void render(SpriteBatch sb);
    void postRender(SpriteBatch sb);

    void debug(ShapeRenderer sr);

    void dispose();

}
