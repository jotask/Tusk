package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.Play;

/**
 * LanterIdle
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class LanterIdle {

    private final PlayerIdle player;
    private Light light;

    private float coneDegree = 35f;
    private float angle = 0f;

    public LanterIdle(PlayerIdle player) {
        this.player = player;
        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();
        light = new ConeLight(rayHandler, 500, Color.WHITE, 10f, player.getPosition().x, player.getPosition().y,
                angle, coneDegree);
        light.setActive(false);
    }

    public void update(){
        light.setActive(player.getCharacter().lantern.on);
        light.setPosition(player.getBody().getPosition());

        final Vector2 mousePos = Play.getInstance().getCamera().getMousePosInGameWorld();

        light.setDirection(player.getAngleFromThis(mousePos));

    }

}
