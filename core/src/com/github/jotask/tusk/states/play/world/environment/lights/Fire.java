package com.github.jotask.tusk.states.play.world.environment.lights;


import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.states.play.world.environment.Environment;

public class Fire extends Luz{

    public Fire(Environment environment) {
        super(environment);
        this.color = Color.RED;
        super.create();
    }

}
