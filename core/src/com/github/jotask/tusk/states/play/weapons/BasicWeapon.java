package com.github.jotask.tusk.states.play.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.bullet.Bullet;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.util.Util;

import java.util.LinkedList;

public class BasicWeapon implements Weapon {

    private Player player;

    private final Timer timer;

    private LinkedList<Bullet> bullets;

    public BasicWeapon(Player player){
        this(1f, player);
    }

    public BasicWeapon(float seconds, Player player) {
        this.timer = new Timer(Util.secondsToNano(seconds));
        this.player = player;
        bullets = new LinkedList<Bullet>();
    }

//    public float getShootAngle(){
////        Vector2 v =  Play.getInstance().getCamera().getMousePosInGameWorld();
////        return v;
//
//        Vector2 centerPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
//
//        Vector2 mouseLoc = Play.getInstance().getCamera().getMousePosInGameWorld();
//
//        Vector2 direction = mouseLoc.sub(centerPosition);
//        float mouseAngle = direction.angle();
//
//        return mouseAngle;
//
//    }

    @Override
    public void shot() {
        if(timer.isFinished()){
            Bullet bullet = Factory.createBullet(player);
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

}
