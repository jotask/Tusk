package com.github.jotask.tusk.states.play.weapons;

import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.util.Util;

public class BasicWeapon implements Weapon {

    private final Timer timer;

    public BasicWeapon(){
        this(1f);
    }

    public BasicWeapon(float seconds) {
        this.timer = new Timer(Util.secondsToNano(seconds));
    }

    @Override
    public void shot() {
        if(timer.isFinished()){
            System.out.println("shoot");
        }
    }

}
