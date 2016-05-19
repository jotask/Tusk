package com.github.jotask.tusk.states.play.entities.bullet;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.states.play.entities.BodyEntity;

public class Bullet extends BodyEntity {

    private float SPEED = 100f;
    private final Timer timer;

    public Bullet(World world, Body body) {
        super(world, body);
        this.timer = new Timer(1f);
        this.body.setLinearVelocity(SPEED, 0);
    }

    public boolean isDead(){
        return timer.isFinished();
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("bullet destroyed");
    }

}
