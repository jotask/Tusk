package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface IEntity {

    void init();

    void update();

    void render(SpriteBatch sb);

    void debug(ShapeRenderer sr);

    void dispose();

}
