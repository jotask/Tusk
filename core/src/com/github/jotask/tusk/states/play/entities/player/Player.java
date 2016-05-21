package com.github.jotask.tusk.states.play.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.engine.controller.DesktopPlayerController;
import com.github.jotask.tusk.engine.controller.PlayerController;
import com.github.jotask.tusk.engine.game.animations.BasicAnimation;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.weapons.MachineGun;
import com.github.jotask.tusk.states.play.weapons.Weapon;

public class Player extends BodyEntity {

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private final float SPEED = 5f;

    private final PlayerController controller;

    private com.github.jotask.tusk.engine.game.animations.Animation animation;

    private Weapon weapon;

    private Lantern lantern;

    public Player(World world, Body body) {
        super(world, body);

        this.body.setUserData(this);

        this.controller = new DesktopPlayerController();
        this.animation = new BasicAnimation();

        this.weapon = new MachineGun(this);

        this.lantern = new Lantern(this);

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

        lantern.update();

        if(controller.up()) {
            velocity.y += SPEED;
        }
        if (controller.down()){
            velocity.y -= SPEED;
        }

        this.applyLinearImpulse(velocity);

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
        //animation.debug(sr, this.body);
        weapon.debug(sr);
    }

    public PlayerController getController() {
        return controller;
    }
}
