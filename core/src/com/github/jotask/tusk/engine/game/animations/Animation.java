package com.github.jotask.tusk.engine.game.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;

public interface Animation {

    void update();

    void render(SpriteBatch sb, Body body);

    void debug(ShapeRenderer sr, Body body);

    void dispose();

}
