package com.github.jotask.tusk.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.play.entities.Entity;
import com.github.jotask.tusk.play.entities.IEntity;

import java.util.LinkedList;

/**
 * EntityUpdater
 *
 * Template for update different types of entities
 *
 * @author Jose Vives Iznardo
 * @since 02/06/2016
 */
public class EntityUpdater<T extends Entity> implements IEntity{

    private final int MAX_SIZE;

    private LinkedList<T> list;

    public EntityUpdater() { this(200); }
    public EntityUpdater(int max_size){
        this.MAX_SIZE = max_size;
        this.list = new LinkedList<T>();
    }

    @Override
    public boolean toDestroy() { return false; }

    @Override
    public void update() {
        LinkedList<T> copy = new LinkedList<T>(list);
        for(T t: list){
            if(t.toDestroy()){
                copy.remove(t);
                t.dispose();
                continue;
            }
            t.update();
        }
        list = copy;
    }

    public boolean add(T newT){
        return list.add(newT);
    }

    public boolean remove(T deleteT){
        if(list.remove(deleteT)){
            deleteT.dispose();
            return true;
        }
        return false;
    }

    @Override
    public void render(SpriteBatch sb) {
        for(T t: list) t.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(T t: list){
            t.debug(sr);
        }
    }

    @Override
    public void dispose() {
        for(T t: list){
            t.dispose();
        }
    }

    public int size(){ return list.size(); }

}
