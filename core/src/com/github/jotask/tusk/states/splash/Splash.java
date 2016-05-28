package com.github.jotask.tusk.states.splash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.states.STATE;
import com.github.jotask.tusk.util.Timer;

public class Splash extends AbstractState {


    private final float TIME = 3;
    private Timer timer;

    private TextureRegion region;

    private Vector2 position;

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        super.setBgColor(Color.BROWN);

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.LOGO_TEXTURE);
        region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

        position = new Vector2(getCamera().position.x - (region.getRegionWidth() / 2f),
                                getCamera().position.y - (region.getRegionHeight() / 2f));

        timer = new Timer(TIME);
    }

    @Override
    public void update() {
        if(timer.isFinished()){
            GameStateManager.get().changeState(STATE.MENU);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.draw(region, position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
    }
}
