package com.github.jotask.tusk.states.play.entities.player;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.engine.game.animations.BasicAnimation;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.weapons.MachineGun;
import com.github.jotask.tusk.states.play.weapons.Weapon;

public class Player extends BodyEntity {

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private final float SPEED = 5f;
    private final float JUMP = 200f;

    private boolean canJump;
    private final PlayerController controller;

    private com.github.jotask.tusk.engine.game.animations.Animation animation;

    private Weapon weapon;

    private Light light;

    private Fixture jumpSensor;

    public Player(World world, Body body) {
        super(world, body);

        this.body.setUserData(this);

        {
            // LIGHT
            RayHandler rayHandler = Play.getInstance().getWorld().getEnviorment().getRayHandler();

            light = new PointLight(rayHandler, 500);

        }

        this.controller = new DesktopPlayerController();
        this.animation = new BasicAnimation();

        this.weapon = new MachineGun(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.05f, 0.05f, new Vector2(0,-0.60f), 0);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;
        jumpSensor = body.createFixture(fd);
        shape.dispose();

    }

    private void applyLinearImpulse(Vector2 targetVelocity){
        Vector2 vel = body.getLinearVelocity();
        float dvx = targetVelocity.x - vel.x;
        float dvy = targetVelocity.y - vel.y;
        body.applyLinearImpulse(new Vector2( body.getMass() * dvx, body.getMass() * dvy), body.getWorldCenter(), true);
    }

    @Override
    public void update() {

        this.animation.update();

        Vector2 velocity = new Vector2();

        if(controller.left()) {
            velocity.x -= SPEED;
        }
        if (controller.right()){
            velocity.x += SPEED;
        }

        if(controller.up()) {
            velocity.y += SPEED;
        }
        if (controller.down()){
            velocity.y -= SPEED;
        }

        applyLinearImpulse(velocity);

        if(canJump && controller.jump()){
            body.applyForceToCenter(new Vector2(0, body.getMass() * JUMP), true);
            canJump = false;
        }
        if (controller.shoot() && weapon != null) {
            weapon.shot();
        }

        light.setPosition(body.getPosition());

    }

    @Override
    public void render(SpriteBatch sb) {
        animation.render(sb, this.body);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        //animation.debug(sr, this.body);
    }

    public void setCanJump(boolean j){
        this.canJump = j;
    }

}
