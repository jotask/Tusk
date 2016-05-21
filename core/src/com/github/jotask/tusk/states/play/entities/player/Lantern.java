package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.Play;

public class Lantern {

    private final Player player;
    private Light light;

    private float coneDegree = 35f;
    private float angle = 0f;

    public Lantern(Player player) {
        this.player = player;
        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();
        light = new ConeLight(rayHandler, 500, Color.WHITE, 10f, player.getPosition().x, player.getPosition().y,
                angle, coneDegree);
    }

    public void update(){
        if(player.getController().light())
            light.setActive(!light.isActive());
        light.setPosition(player.getBody().getPosition());

        final Vector2 mousePos = Play.getInstance().getCamera().getMousePosInGameWorld();

        light.setDirection(player.getAngleFromThis(mousePos));

    }

}
