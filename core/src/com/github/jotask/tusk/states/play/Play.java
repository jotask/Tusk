package com.github.jotask.tusk.states.play;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.AbstractState;
import com.github.jotask.tusk.states.play.entities.EntityManager;
import com.github.jotask.tusk.states.play.entities.Player;
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
        this.entityManager = new EntityManager();

        player = new Player(world, this.getCamera());

    }

    @Override
    public void update() {
        super.update();
        this.world.update();
        player.update();
        this.entityManager.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.world.getLevel().render(this.getCamera());
        sb.end();
        sb.begin();
        player.render(sb);
        this.entityManager.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.world.debug(this.getCamera().combined);
        player.debug(sr);
        this.entityManager.debug(sr);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.world.dispose();
        player.dispose();
        entityManager.dispose();
    }
}
