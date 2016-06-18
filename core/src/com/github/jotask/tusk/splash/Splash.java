package com.github.jotask.tusk.splash;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.STATE;
import com.github.jotask.tusk.util.Timer;
import com.github.jotask.tusk.util.Util;

public class Splash extends AbstractState {

    private final float TIME = 3;
    private Timer timer;

    private TextureRegion region;

    private Vector2 position;

    public Splash(final Tusk tusk) { super(tusk, 2f); }

    @Override
    public void init() {
        this.setBgColor(Util.getColorFromHex(255,166,158, 1f));

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.AIKO);
        region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

        position = new Vector2(getCamera().position.x - (region.getRegionWidth() / 2f),
                                getCamera().position.y - (region.getRegionHeight() / 2f));

        timer = new Timer(TIME);

    }

    @Override
    public void update() {
        if(timer.isFinished()) tusk.getGsm().changeState(STATE.MENU);
        this.camera.zoom -= 0.01f;
        this.camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.draw(region, position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
    }

}
