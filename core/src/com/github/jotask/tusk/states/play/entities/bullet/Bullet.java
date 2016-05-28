package com.github.jotask.tusk.states.play.entities.bullet;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.BodyEntity;

public class Bullet extends BodyEntity {

    public static final float RADIUS = 0.1f;

    private BodyEntity shooter;

    public static float SPEED = 100f;
    private final Timer timer;

    private PointLight light;

    public Bullet(Body body, BodyEntity shooter) {
        super(body);

        this.shooter = shooter;
        this.timer = new Timer(1f);

        RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();

        this.light = new PointLight(rayHandler, 10);
        light.setSoft(false);
        light.setXray(true);
        light.setDistance(1f);
        light.setColor(Color.ORANGE);
        light.attachToBody(body);

        Vector2 targetPosition = Play.getInstance().getCamera().getMousePosInGameWorld();
        Vector2 currentPosition = body.getPosition();

        float x = targetPosition.x - currentPosition.x;
        float y = targetPosition.y - currentPosition.y;

        Vector2 vel = new Vector2(x, y);
        vel.nor();
        vel.x *= SPEED;
        vel.y *= SPEED;

        body.setLinearVelocity(vel);

    }

    @Override
    public void update() { }

    public boolean isDead(){
        return timer.isFinished();
    }



    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.ellipse(body.getPosition().x - (Bullet.RADIUS), body.getPosition().y - (Bullet.RADIUS), Bullet.RADIUS * 2, Bullet.RADIUS * 2);
    }

    @Override
    public void dispose() {
        super.dispose();
        light.remove();
    }

    public BodyEntity getShooter() { return shooter; }

}
