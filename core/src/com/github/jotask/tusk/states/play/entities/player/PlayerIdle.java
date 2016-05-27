package com.github.jotask.tusk.states.play.entities.player;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.states.play.entities.BodyEntity;

/**
 * Created by Jota on 27/05/2016.
 */
public class PlayerIdle extends BodyEntity {

    public PlayerIdle(World world, Body body) {
        super(world, body);
    }

}

