package com.github.jotask.tusk.engine.game;

import com.github.jotask.tusk.util.Util;

public class Timer {

    private long delay;
    private long lastTime;

    public Timer (float seconds){
        this(Util.secondsToNano(seconds));
    }

    public Timer(long delay) {
        this.delay = delay;
        this.lastTime = System.nanoTime();
    }

    public boolean isFinished(){
        if(lastTime + delay < System.nanoTime()) {
            reset();
            return true;
        }
        return false;
    }

    public void reset(){
        lastTime = System.nanoTime();
    }

}
