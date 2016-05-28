package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.ConeLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.states.play.Play;

public class Lantern {

    private final Player player;
    private ConeLight light;

    private float coneDegree = 35f;
    private float angle = 0f;

    public Lantern(Player player) {
        this.player = player;
        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();
        light = new ConeLight(rayHandler, 500, Color.WHITE, 10f, player.getPosition().x, player.getPosition().y,
                angle, coneDegree);
        light.setActive(false);
    }

    public void update(){
        if(player.getController().light())
            light.setActive(!light.isActive());
        light.setPosition(player.getBody().getPosition());

        this.angle = player.getAngleFromThis(Play.getInstance().getCamera().getMousePosInGameWorld());

        light.setDirection(angle);

    }

    public boolean isOn() {
        return this.light.isActive();
    }

    public float getAngle(){ return this.angle; }

}
