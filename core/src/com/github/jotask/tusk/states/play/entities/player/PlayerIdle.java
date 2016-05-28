package com.github.jotask.tusk.states.play.entities.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.game.animations.Animation;
import com.github.jotask.tusk.engine.game.animations.BasicAnimation;
import com.github.jotask.tusk.engine.game.animations.PlayerAnimation;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.states.play.entities.BodyEntity;

/**
 * Created by Jota on 27/05/2016.
 */
public class PlayerIdle extends BodyEntity {

    private final Network.Character character;

    public boolean disconnected;

    public static final int width = 16; // 15
    public static final int height = 16; // 20

    private Animation animation;

    private LanterIdle lanterIdle;

    public PlayerIdle(Body body, Network.Character character) {
        super(body);

        this.character = character;

        this.body.setUserData(this);

        this.lanterIdle = new LanterIdle(this);

        this.animation = new PlayerAnimation();

    }

    @Override
    public void update() {
        this.lanterIdle.update();
        this.animation.update();
        this.animation.changeAnimation(BasicAnimation.ANIMATIONS.IDLE);
        this.body.setTransform(character.position.x, character.position.y, character.angle);
    }

    public void setData(Network.Character c){
        this.character.position = c.position;
        this.character.angle = c.angle;
        this.lanterIdle.setData(c.lantern);
    }

    @Override
    public void render(SpriteBatch sb) {
        animation.render(sb, this.body);
    }

    @Override
    public void debug(ShapeRenderer sr) { }

    public Network.Character getCharacter() { return character; }
}

