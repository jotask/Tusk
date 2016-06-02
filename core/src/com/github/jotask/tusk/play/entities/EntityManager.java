package com.github.jotask.tusk.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.engine.EntityUpdater;
import com.github.jotask.tusk.play.entities.bullet.Bullet;
import com.github.jotask.tusk.play.entities.player.PlayerIdle;

public class EntityManager implements IEntity{

    private static EntityManager instance;

    private EntityUpdater<Bullet> bullets;
    private EntityUpdater<PlayerIdle> playerIdle;

    public static EntityManager get(){
        if(instance == null) instance = new EntityManager();
        return instance;
    }

    public EntityManager() {
        this.bullets = new EntityUpdater<Bullet>();
        this.playerIdle = new EntityUpdater<PlayerIdle>();
    }

    @Override
    public boolean toDestroy() { return false; }

    @Override
    public void update() {
        this.bullets.update();
        this.playerIdle.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        this.bullets.render(sb);
        this.playerIdle.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        this.bullets.debug(sr);
        this.playerIdle.debug(sr);
    }

    @Override
    public void dispose() {
        this.bullets.dispose();
        this.playerIdle.dispose();
    }

    public EntityUpdater<Bullet> getBullets() { return bullets; }
    public EntityUpdater<PlayerIdle> getPlayerIdle() { return playerIdle; }

}
