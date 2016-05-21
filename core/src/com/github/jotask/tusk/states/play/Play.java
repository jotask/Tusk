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

    private Mundo world;
    private EntityManager entityManager;
    private Player player;

    @Override
    public void init() {
        super.init();
        this.setBgColor(Color.BLACK);
        this.world = new Mundo();
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
        this.world.getLevel().render(this.getCamera());
        sb.end();
        sb.begin();
        this.player.render(sb);
        this.entityManager.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.world.debug(this.getCamera().combined);
        this.player.debug(sr);
        this.entityManager.debug(sr);

        final float width = 100;
        final float size = 0.2f;

//        sr.line(width,0,0,-width,0,0);
//        for(float i = -width; i < width; i += 1f){
//            sr.line(i, size, 0, i, -size, 0);
//            sr.line(size, i, 0, -size, i, 0);
//        }
//        sr.line(0,width,0,0,-width,0);

        camera.debug(sr);

    }

    @Override
    public void dispose() {
        super.dispose();
        this.world.dispose();
        this.player.dispose();
        this.entityManager.dispose();
    }

    public Player getPlayer() {
        return player;
    }
    public Mundo getWorld() { return world; }
}
