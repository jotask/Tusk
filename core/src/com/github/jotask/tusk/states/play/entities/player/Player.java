package com.github.jotask.tusk.states.play.entities.player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.jotask.tusk.engine.game.animations.BasicAnimation;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.weapons.BasicWeapon;
import com.github.jotask.tusk.states.play.weapons.MachineGun;
import com.github.jotask.tusk.states.play.weapons.Weapon;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.entities.TypeEntity;

public class Player extends BodyEntity {

    //test

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private final float SPEED = 5f;
    private final float JUMP = 200f;

    private OrthographicCamera camera;

    private boolean canJump;

    private final PlayerController controller;

    private com.github.jotask.tusk.engine.game.animations.Animation animation;

    private Weapon weapon;

    private Fixture jumpSensor;

    public Player(Play state) {
        super(TypeEntity.PLAYER, state.getWorld(),
                state.getWorld().getLevel().getPlayerSpawn(),
                new Vector2(width, height));

        this.body.setUserData(this);

        this.camera = state.getCamera();
        this.controller = new DesktopPlayerController();
        this.animation = new BasicAnimation();

        this.weapon = new BasicWeapon();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.05f, 0.05f, new Vector2(0,-0.5f), 0);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.isSensor = true;
        jumpSensor = body.createFixture(fd);
        shape.dispose();

    }

    private void applyLinearImpulse(Vector2 targetVelocity){
        Vector2 vel = body.getLinearVelocity();
        float dvx = targetVelocity.x - vel.x;
        float dvy = 0f;
        body.applyLinearImpulse(new Vector2( body.getMass() * dvx, body.getMass() * dvy), body.getWorldCenter(), true);
    }

    @Override
    public void update() {

        this.animation.update();

        if(controller.left()) {
            applyLinearImpulse(new Vector2(-SPEED, 0));
        }
        if (controller.right()){
            applyLinearImpulse(new Vector2(SPEED, 0));
        }

        if(canJump && controller.jump()){
            body.applyForceToCenter(new Vector2(0, body.getMass() * JUMP), true);
            canJump = false;
        }
        if (controller.shoot() && weapon != null) {
            weapon.shot();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        animation.render(sb, this.body);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        animation.debug(sr, this.body);
    }

    public void setCanJump(boolean j){
        this.canJump = j;
    }

}
