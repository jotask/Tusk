package com.github.jotask.tusk.play.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.play.game.entities.EntityManager;
import com.github.jotask.tusk.play.game.entities.player.Player;
import com.github.jotask.tusk.play.game.ui.HUD;
import com.github.jotask.tusk.play.game.world.Mundo;
import com.github.jotask.tusk.play.game.world.NewWorld;
import com.github.jotask.tusk.states.AbstractState;

public abstract class Game extends AbstractState {

    private static Game instance;
    public static Game getInstance(){ return instance; }

    private HUD hud;
    private Mundo world;
    private EntityManager entityManager;
    private Player player;

    private NewWorld newWorld;

    protected Game(final Tusk tusk) { super(tusk);
        Game.instance = this;
    }

    @Override
    public void init() {
        super.init();
        this.setBgColor(Color.BLACK);
        this.world = new Mundo();
        this.world.init();
        this.entityManager = EntityManager.get();
        this.player = Factory.Players.createPlayer(this);
        this.hud = new HUD(this);

        this.newWorld = new NewWorld();

    }


    @Override
    public void update() {
        super.update();
        this.world.update();
        this.newWorld.update();
        this.player.update();
        this.camera.follow(player);
        this.entityManager.update();
        this.hud.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        this.world.render(sb, this.camera);
        this.newWorld.render(sb);
        this.entityManager.render(sb);
        this.player.render(sb);
        this.hud.render(sb);
    }

    @Override
    public void postRender(SpriteBatch sb) {
        this.getWorld().postRender(sb);
        this.hud.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        this.world.debug(sr, this.getCamera().combined);
        this.newWorld.debug(sr);
        this.entityManager.debug(sr);
        this.player.debug(sr);
        this.hud.debug(sr);
    }

    @Override
    public void dispose() {
        this.newWorld.dispose();
        this.player.dispose();
        this.entityManager.dispose();
        this.world.dispose();
        this.hud.dispose();
        Game.instance = null;
    }

    public Player getPlayer() {
        return player;
    }
    public Mundo getWorld() { return world; }

}
