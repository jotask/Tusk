package com.github.jotask.tusk.play.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.game.animations.Animation;
import com.github.jotask.tusk.engine.game.animations.PlayerAnimation;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.play.entities.BodyEntity;
import com.github.jotask.tusk.play.items.weapons.IdleWeapon;

/**
 * Created by Jota on 27/05/2016.
 */
public class PlayerIdle extends BodyEntity {

    private final Network.Character character;

    protected boolean disconnected;

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private Animation animation;

    private LanternIdle lanternIdle;

    private IdleWeapon weapon;

    public PlayerIdle(Body body, Network.Character character) {
        super(body);

        this.character = character;

        this.body.setUserData(this);

        this.lanternIdle = new LanternIdle(this);

        this.animation = new PlayerAnimation();

        this.weapon = Factory.Weapons.createIdleWeapon(character.weapon);

    }

    @Override
    public void update() {
        this.lanternIdle.update();
        this.animation.update();
        this.weapon.update();
        this.body.setTransform(character.position.x, character.position.y, character.angle);
    }

    public void setData(Network.Character c){
        this.character.position = c.position;
        this.character.angle = c.angle;
        this.lanternIdle.setData(c.lantern);
        this.weapon.setData(c.weapon);
    }

    @Override
    public void render(SpriteBatch sb) { this.animation.render(sb, this.body); }

    @Override
    public void debug(ShapeRenderer sr) { }

    public Network.Character getCharacter() { return character; }

    @Override
    public void dispose() {
        super.dispose();
        this.animation.dispose();
        this.lanternIdle.dispose();
        this.weapon.dispose();
    }

    @Override
    public boolean toDestroy() { return disconnected; }

    public void setDisconnected(boolean disconnected) { this.disconnected = disconnected; }

}

