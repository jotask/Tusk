package com.github.jotask.tusk.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Entity implements IEntity {

    private boolean toDestroy;

    @Override
    public boolean toDestroy() { return toDestroy; }

    @Override
    public abstract void update();

    @Override
    public abstract void render(SpriteBatch sb);

    @Override
    public abstract void debug(ShapeRenderer sr);

    @Override
    public abstract void dispose();
}
