package com.github.jotask.tusk.states.play.entities.player;

public interface PlayerController {

    boolean left();
    boolean right();
    boolean up();
    boolean down();

    boolean jump();

    boolean shoot();

}
