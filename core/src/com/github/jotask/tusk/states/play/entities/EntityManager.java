package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.play.entities.bullet.Bullet;

import java.util.LinkedList;

public class EntityManager implements IEntity{

    private static EntityManager instance;

    public static EntityManager get(){
        if(instance == null)
            instance = new EntityManager();
        return instance;
    }

    private final int MAX_BULLETS = 10;
    private LinkedList<Bullet> bullets;

    private EntityManager() {
        this.bullets = new LinkedList();
    }

    @Override
    public void init() {}

    @Override
    public void update() {
        LinkedList<Bullet> toDestroy = new LinkedList<Bullet>();
        for(Bullet b: bullets){
            if(b.isDead()){
                toDestroy.add(b);
                continue;
            }
        }
        for(Bullet b: toDestroy){
            bullets.remove(b);
            b.dispose();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        for(Bullet b: bullets){
            b.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        for(Bullet b: bullets){
            b.debug(sr);
        }
    }

    @Override
    public void dispose() {
        for(Bullet b: bullets){
            b.dispose();
        }
    }

    public void addBullet(Bullet bullet){
        if(bullets.size() > MAX_BULLETS)
            return;

        bullets.add(bullet);

    }

    public int bulletsSize(){ return bullets.size(); };

}
