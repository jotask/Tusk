package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.ConeLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.states.play.Play;

/**
 * Lantern Idle
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class LanternIdle {

    private final Network.Lantern lantern;

    private final PlayerIdle player;
    private ConeLight light;

    private float coneDegree = 35f;
    private float angle = 0f;

    public LanternIdle(PlayerIdle player) {
        this.player = player;
        this.lantern = player.getCharacter().lantern;
        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();
        light = new ConeLight(rayHandler, 500, Color.WHITE, 10f, player.getPosition().x, player.getPosition().y,
                angle, coneDegree);
        light.setActive(false);
    }

    public void update(){
        light.setActive(lantern.on);
        if(light.isActive()) {
            light.setPosition(player.getBody().getPosition());

            light.setDirection(lantern.angle);
        }

    }

    public void setData(Network.Lantern lantern){
        this.lantern.on = lantern.on;
        this.lantern.angle = lantern.angle;
    }

}
