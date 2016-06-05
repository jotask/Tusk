package com.github.jotask.tusk.play.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.controller.DesktopPlayerController;
import com.github.jotask.tusk.engine.controller.PlayerController;
import com.github.jotask.tusk.engine.game.animations.Animation;
import com.github.jotask.tusk.engine.game.animations.BasicAnimation;
import com.github.jotask.tusk.engine.game.animations.PlayerAnimation;
import com.github.jotask.tusk.play.Inventory;
import com.github.jotask.tusk.play.entities.BodyEntity;
import com.github.jotask.tusk.play.items.weapons.MachineGun;

public class Player extends BodyEntity {

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private final float SPEED = 5f;

    private final PlayerController controller;

    private Animation animation;

    private Inventory inventory;

    public Player(Body body) {
        super(body);
        this.body.setUserData(this);
        this.inventory = new Inventory(this);
        Player instance = this;
        this.inventory.equipLantern(new Lantern(instance));
        MachineGun gun = new MachineGun();
        gun.pickUp(this.inventory);
        this.controller = new DesktopPlayerController();
        this.animation = new PlayerAnimation();
    }

    private void applyLinearImpulse(Vector2 targetVelocity){
        Vector2 vel = body.getLinearVelocity();
        float dvx = targetVelocity.x - vel.x;
        float dvy = targetVelocity.y - vel.y;
        body.applyLinearImpulse(new Vector2( body.getMass() * dvx, body.getMass() * dvy), body.getWorldCenter(), true);
    }

    @Override
    public boolean toDestroy() { return false; }

    @Override
    public void update() {
        this.animation.update();
        this.inventory.update();

        animation.changeAnimation(BasicAnimation.ANIMATIONS.IDLE);
        Vector2 velocity = new Vector2();
        if(controller.left()) {
            velocity.x -= SPEED;
            this.animation.direction(BasicAnimation.DIRECTION.LEFT);
        }
        if (controller.right()){
            velocity.x += SPEED;
            this.animation.direction(BasicAnimation.DIRECTION.RIGHT);
        }


        if(controller.up()) {
            velocity.y += SPEED;
        }
        if (controller.down()){
            velocity.y -= SPEED;
        }

        if(!velocity.isZero()){
            animation.changeAnimation(BasicAnimation.ANIMATIONS.WALK);
        }

        this.applyLinearImpulse(velocity);

        if (controller.shoot() && this.inventory.getWeapon() != null) {
            this.inventory.getWeapon().shot();
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        animation.render(sb, this.body);
    }

    @Override
    public void debug(ShapeRenderer sr) { }

    public PlayerController getController() {
        return controller;
    }

    public Inventory getInventory() { return this.inventory; }

}
