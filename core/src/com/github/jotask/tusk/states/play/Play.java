package com.github.jotask.tusk.states.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.engine.AbstractState;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.states.play.entities.EntityManager;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.states.play.world.Mundo;

public class Play extends AbstractState {

    private static Play state;

    public static Play getInstance(){
        if(state == null){
            throw new UnsupportedOperationException("Can't get instance, is not the current state");
        }
        return state;
    }

    private Mundo world;
    private EntityManager entityManager;
    private Player player;

    @Override
    public void init() {
        super.init();
        this.setBgColor(Color.BLACK);
        state = this;

        this.world = new Mundo();
        this.world.init();

        this.entityManager = EntityManager.get();
        this.player = Factory.createPlayer(this);
    }

    @Override
    public void update() {
        super.update();
        this.world.update();
        this.player.update();
        this.camera.follow(player);
        this.entityManager.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.world.render(sb, this.camera);
        this.player.render(sb);
        this.entityManager.render(sb);
    }

    @Override
    public void postRender(SpriteBatch sb) { this.getWorld().postRender(sb); }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.world.debug(sr, this.getCamera().combined);
        this.player.debug(sr);
        this.entityManager.debug(sr);
    }

    @Override
    public void dispose() {
        this.player.dispose();
        this.entityManager.dispose();
        this.world.dispose();
        Play.state = null;
    }

    public Player getPlayer() {
        return player;
    }
    public Mundo getWorld() { return world; }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
