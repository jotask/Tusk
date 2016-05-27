package com.github.jotask.tusk.states.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.AbstractState;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.online.client.TuskClient;
import com.github.jotask.tusk.states.play.entities.EntityManager;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.states.play.world.Mundo;

import java.io.IOException;

public class Play extends AbstractState {

    private static Play state;

    private TuskClient client;

    public static Play getInstance(){
        if(state == null){
            throw new UnsupportedOperationException("Can't get instance, is not the current state");
        }
        return state;
    }

    private Mundo world;
    private EntityManager entityManager;
    private Player player;

    private Rectangle rectangle;

    @Override
    public void init() {
        super.init();
        this.setBgColor(Color.BLACK);
        state = this;

        this.world = new Mundo();
        this.world.init();

        this.entityManager = EntityManager.get();
        this.player = Factory.createPlayer(this);

        this.rectangle = new Rectangle(10f,10f,1f,1f);

        try {
            this.client = new TuskClient(this);
        } catch (IOException e) {
            System.err.println("Impossible connect to the server");
        }

    }

    @Override
    public void update() {
        super.update();
        this.world.update();
        if(this.client != null) this.client.update();
        this.player.update();
        this.camera.follow(player);
        this.entityManager.update();

        if(this.client != null)
            this.client.sendPlayer(this.getPlayer());

        up();
    }

    private void up(){
        Vector2 p = new Vector2(rectangle.x, rectangle.y);
        p.y += (float) (0.1f * Math.sin(p.x));
//        System.out.println(p.y);
        rectangle.setPosition(p);
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
        sr.box(rectangle.x, rectangle.y, 0, rectangle.width, rectangle.height, 0);
    }

    @Override
    public void dispose() {
        this.player.dispose();
        this.entityManager.dispose();
        this.world.dispose();
        if(this.client != null) this.client.dispose();
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
