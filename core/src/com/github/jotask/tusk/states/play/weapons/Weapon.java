package com.github.jotask.tusk.states.play.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Weapon {

    void shot();

    void render(SpriteBatch sb);

    void debug(ShapeRenderer sr);
}
