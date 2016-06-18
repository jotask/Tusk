package com.github.jotask.tusk.engine.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * LoadingBar
 *
 * @author Jose Vives Iznardo
 * @since 13/06/2016
 */
public class LoadingBar {

    OrthographicCamera camera;

    Rectangle bounds;

    Rectangle loading;

    public LoadingBar(OrthographicCamera camera) {

        this.camera = camera;

        final float offset = 10f;

        final int SCALE = (int) (Gdx.graphics.getWidth() / camera.viewportWidth);

        {
            float x = camera.position.x - (camera.viewportWidth / 2f);
            x += offset;

            float y = camera.position.y - (camera.viewportHeight / 2f);
            y += offset;

            float w = camera.viewportWidth;
            w -= offset * 2;

            float h = camera.viewportHeight;
            h -= offset * 2;

            bounds = new Rectangle(x * SCALE, y * SCALE, w * SCALE, h * SCALE);
        }

        {
            loading = new Rectangle();
            loading.setPosition(bounds.x + offset, bounds.y + offset);
            loading.setSize(bounds.width - offset * 2, bounds.height - offset * 2);
        }

        max = loading.width - offset;

    }

    final float max;

    public void update(float progress){
        float w = (max * progress) / 1f;
        loading.width = w;
    }

    public void render(ShapeRenderer sr){
        sr.set(ShapeRenderer.ShapeType.Line);
        renderRectangle(sr, bounds);
        sr.set(ShapeRenderer.ShapeType.Filled);
        renderRectangle(sr, loading);
    }

    private void renderRectangle(ShapeRenderer sr, Rectangle r){
        sr.box(r.x, r.y, 0, r.width, r.height, 0);
    }

}
