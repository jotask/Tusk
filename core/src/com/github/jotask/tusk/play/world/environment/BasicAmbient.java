package com.github.jotask.tusk.play.world.environment;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;

public class BasicAmbient implements Ambient{

    private final RayHandler rayHandler;

    private Color color;
    private float alpha;
    private float intensity;

    public BasicAmbient(RayHandler rayHandler) {
        this.rayHandler = rayHandler;

        this.color = Color.BLUE;
        alpha = .5f;
        this.intensity = 0f;

        this.rayHandler.setAmbientLight(0f);
        this.rayHandler.setAmbientLight(color.r, color.g, color.b, alpha);

    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        rayHandler.dispose();
    }
}
