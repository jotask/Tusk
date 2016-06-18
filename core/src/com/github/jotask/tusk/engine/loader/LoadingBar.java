package com.github.jotask.tusk.engine.loader;

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

    private final float HEIGHT = 100f;

    public LoadingBar(OrthographicCamera camera) {
        this.camera = camera;

        float offset = 10f;

        float x = camera.position.x - (camera.viewportWidth  / 2f);
        x += offset;

        float y = camera.position.y - (HEIGHT / 2f);
        y += offset;

        float w = camera.viewportWidth;
        w -= offset * 2;

        float h = HEIGHT;
        h -= offset * 2;

        bounds = new Rectangle(x, y, w, h);

        {
            loading = new Rectangle();
            loading.setPosition(bounds.x + offset, bounds.y + offset);
            loading.setSize(bounds.width - offset * 2, bounds.height - offset * 2);
        }

        max = loading.width;

    }

    final float max;

    public void update(float progress){
        float w = (max * progress) / 1f;
        loading.width = w;
    }

    private void finishLoading(){
        System.out.println("finished loading");
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
