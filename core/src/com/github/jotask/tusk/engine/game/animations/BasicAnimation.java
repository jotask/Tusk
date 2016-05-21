package com.github.jotask.tusk.engine.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.util.Util;

public class BasicAnimation implements Animation {

    final int width = 15; // 15
    final int height = 20; // 20

    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;
    private com.badlogic.gdx.graphics.g2d.Animation walkAnimation;

    private float stateTime;

    private boolean flip = true;

    public BasicAnimation() {
        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.PLAYER_TEXTURE);
        walkFrames = new TextureRegion[2];
        walkFrames[0] = new TextureRegion(texture, 9, 12, width, height);
        walkFrames[1] = new TextureRegion(texture, 42, 12, width, height);
        walkAnimation = new com.badlogic.gdx.graphics.g2d.Animation(0.25f, walkFrames);
    }

    @Override
    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    }

    @Override
    public void render(SpriteBatch sb, Body body) {
        Util.Render.render(sb, currentFrame, body);
    }

    @Override
    public void debug(ShapeRenderer sr, Body body) { Util.Render.debug(sr, currentFrame, body); }

    @Override
    public void dispose() {

    }

}
