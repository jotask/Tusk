package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.LinkedList;

public class EntityManager implements IEntity{

    private LinkedList<IEntity> entities;

    public EntityManager() {
        this.entities = new LinkedList();
    }

    @Override
    public void init() {}

    @Override
    public void update() {
        for(IEntity e: entities){
            e.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(IEntity e: entities){
            e.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(IEntity e: entities){
            e.debug(sr);
        }
    }

    @Override
    public void dispose() {
        for(IEntity e: entities){
            e.dispose();
        }
    }
}
