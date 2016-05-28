package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.play.entities.bullet.Bullet;
import com.github.jotask.tusk.states.play.entities.player.PlayerIdle;

import java.util.LinkedList;

public class EntityManager implements IEntity{

    private static EntityManager instance;

    public static EntityManager get(){
        if(instance == null)
            instance = new EntityManager();
        return instance;
    }

    private final int MAX_BULLETS = 10;
    private int current_num_bullets = 0;

    private LinkedList<Entity> entities;

    private EntityManager() {
        this.entities = new LinkedList<Entity>();
    }

    @Override
    public void update() {
        LinkedList<Entity> toDestroy = new LinkedList<Entity>();
        for(Entity e: entities){

            if(e instanceof Bullet){
                Bullet bullet = (Bullet) e;
                if(bullet.isDead()){
                    current_num_bullets--;
                    toDestroy.add(e);
                    continue;
                }
            }else if(e instanceof PlayerIdle){
                PlayerIdle playerIdle = (PlayerIdle) e;
                if(playerIdle.disconnected){
                    toDestroy.add(e);
                    continue;
                }
            }

            e.update();

        }

        for(Entity e: toDestroy){
            entities.remove(e);
            e.dispose();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        for(Entity e: entities){
            e.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(Entity e: entities){
            e.debug(sr);
        }
    }

    public void add(Entity entity){
        if(entity == null) return;

        if(entity instanceof Bullet){
            current_num_bullets++;
        }

        this.entities.add(entity);
    }

    @Override
    public void dispose() {
        for (Entity e : entities) {
            e.dispose();
        }
    }

    public int bulletsSize(){ return current_num_bullets; }

}
