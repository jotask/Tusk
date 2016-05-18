package com.github.jotask.tusk.util;

public class Timer {

    private long delay;
    private long lastTime;

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
