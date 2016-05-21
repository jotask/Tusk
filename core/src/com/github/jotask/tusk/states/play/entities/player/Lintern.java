package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.github.jotask.tusk.states.play.Play;

public class Lintern {

    private final Player player;
    private Light light;

    public Lintern(Player player) {
        this.player = player;
        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();
        light = new PointLight(rayHandler, 500);
    }

    public void update(){
        if(player.getController().light())
            light.setActive(!light.isActive());
        light.setPosition(player.getBody().getPosition());
    }

}
