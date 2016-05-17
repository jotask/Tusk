package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Debug extends AbstractState{

    private boolean isEnabled = true;

    private BitmapFont font;

    public Debug() { this.init(); }

    @Override
    public void init() {
        this.camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.font = new BitmapFont();

    }

    @Override
    public void update() {
        super.update();
        if(Gdx.input.isKeyJustPressed(Input.Keys.F3))
            isEnabled = !isEnabled;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        final float offset = 10f;
        float y = camera.viewportHeight / 2 - offset;
        font.draw(sb, "FPS: " + Gdx.graphics.getFramesPerSecond(), offset + camera.position.x - (camera.viewportWidth / 2), y);
        sb.end();
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.setProjectionMatrix(camera.combined);
        sr.begin();
        sr.end();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
