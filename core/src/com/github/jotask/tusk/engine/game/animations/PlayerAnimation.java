package com.github.jotask.tusk.engine.game.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.jotask.tusk.engine.game.AssetManager;

public class PlayerAnimation extends BasicAnimation{

    public PlayerAnimation() {
        super();

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.PLAYER_TEXTURE);

        TextureRegion[] walkFrames = new TextureRegion[2];
        walkFrames[0] = new TextureRegion(texture, 9, 12, width, height);
        walkFrames[1] = new TextureRegion(texture, 42, 12, width, height);

        Animation walk = new com.badlogic.gdx.graphics.g2d.Animation(FRAMES, walkFrames);

        TextureRegion[] idleFrames = new TextureRegion[1];
        idleFrames[0] = new TextureRegion(texture, 9, 12, width, height);

        Animation idle = new com.badlogic.gdx.graphics.g2d.Animation(FRAMES, idleFrames);

        this.addAnimation(ANIMATIONS.WALK, walk);
        this.addAnimation(ANIMATIONS.IDLE, idle);

    }

}
