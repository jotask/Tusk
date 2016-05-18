package com.github.jotask.tusk.states.play;

import com.github.jotask.tusk.util.Timer;

public class Weapon implements IWeapon {

    private final Timer timer;

    public Weapon(){
        this(1f);
    }

    public Weapon(float seconds) {
        this.timer = new Timer((long)(seconds * 1000000000));
    }

    @Override
    public void shot() {
        if(timer.isFinished())
            System.out.println("shot");
    }

}
