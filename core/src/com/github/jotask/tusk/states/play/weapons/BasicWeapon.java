package com.github.jotask.tusk.states.play.weapons;

import com.github.jotask.tusk.engine.GameStateManager;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.states.play.entities.EntityManager;
import com.github.jotask.tusk.states.play.entities.bullet.Bullet;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.util.Util;

import java.util.LinkedList;

public class BasicWeapon implements Weapon {

    private Player player;

    private final Timer timer;

    private final int MAX_BULLET = 10;
    private LinkedList<Bullet> bullets;

    public BasicWeapon(Player player){
        this(1f, player);
    }

    public BasicWeapon(float seconds, Player player) {
        this.timer = new Timer(Util.secondsToNano(seconds));
        this.player = player;
        bullets = new LinkedList<Bullet>();
    }

    @Override
    public void shot() {
        if(timer.isFinished()){
            if(bullets.size() <= MAX_BULLET){
                EntityManager.get().addBullet(Factory.createBullet(player));
            }
        }
    }

}
