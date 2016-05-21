package com.github.jotask.tusk.engine.game.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.util.Util;

import java.util.HashMap;

public abstract  class BasicAnimation implements Animation {

    public enum DIRECTION {LEFT, RIGHT, UP, DOWN}
    public enum ANIMATIONS{ IDLE, WALK, DEAD }

    private ANIMATIONS currentAnimations = ANIMATIONS.IDLE;

    final int width = 16; // 15
    final int height = 16; // 20

    private TextureRegion currentFrame;

    private boolean flipX, flipY;

    public static final float FRAMES = 0.25f;

    private boolean flip;

    private float stateTime;

    HashMap<ANIMATIONS, com.badlogic.gdx.graphics.g2d.Animation> animations;

    public BasicAnimation() {
        animations = new HashMap<ANIMATIONS, com.badlogic.gdx.graphics.g2d.Animation>(ANIMATIONS.values().length);
    }

    protected void addAnimation(ANIMATIONS a, com.badlogic.gdx.graphics.g2d.Animation animation){
        animations.put(a, animation);
    }

    public void changeAnimation(ANIMATIONS anim){
        currentAnimations = anim;
    }

    public void direction(DIRECTION dir){
        switch (dir){
            case LEFT:
                if(!currentFrame.isFlipX())
                    currentFrame.flip(!currentFrame.isFlipX(), currentFrame.isFlipY());
                break;
            case RIGHT:
                if(currentFrame.isFlipX())
                    currentFrame.flip(currentFrame.isFlipX(), currentFrame.isFlipY());
                break;
            case UP:
            case DOWN:
                default:
                break;
        }
    }

    @Override
    public void update() {
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animations.get(currentAnimations).getKeyFrame(stateTime, true);
    }

    @Override
    public void render(SpriteBatch sb, Body body) { Util.Render.render(sb, currentFrame, body); }

    @Override
    public void debug(ShapeRenderer sr, Body body) { Util.Render.debug(sr, currentFrame, body); }

    @Override
    public void dispose() {

    }

}
