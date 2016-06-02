package com.github.jotask.tusk.play.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.play.Play;
import com.github.jotask.tusk.play.entities.player.Player;
import com.github.jotask.tusk.util.Timer;
import com.github.jotask.tusk.util.Util;

public class BasicWeapon implements Weapon {

    private Player player;

    private int needsToFire;

    private final Timer timer;

    public BasicWeapon(Player player){
        this(1f, player);
    }

    public BasicWeapon(float seconds, Player player) {
        this.timer = new Timer(Util.secondsToNano(seconds));
        this.player = player;
    }

    @Override
    public void shot() {
        if(timer.isFinished()){
            Factory.Entities.createBullet(player);
            needsToFire++;
        }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {
        Vector2 p = Play.getInstance().getCamera().getMousePosInGameWorld();
        sr.line(player.getPosition().x, player.getPosition().y, p.x, p.y);
    }

    @Override
    public void dispose() { }

    public int getNeedsToFire() {
        int tmp = needsToFire;
        needsToFire = 0;
        return tmp;
    }
}
