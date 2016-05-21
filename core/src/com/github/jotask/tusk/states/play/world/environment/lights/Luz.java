package com.github.jotask.tusk.states.play.world.environment.lights;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.world.environment.Ambient;
import com.github.jotask.tusk.states.play.world.environment.Environment;

public abstract class Luz implements Ambient {

    protected Environment environment;
    protected Color color = Color.WHITE;
    protected boolean xRay;
    protected float distance = 10f;
    protected boolean soft;
    protected float alpha = 1f;
    protected final int RAYS = 10;

    private PointLight light;

    public Luz(Environment environment){
        this(environment, new Vector2(0,0));
    }

    public Luz(Environment environment, Vector2 position) { this.environment = environment; }

    protected void create(){
        light = new PointLight(environment.getRayHandler(), RAYS);
        light.setDistance(distance);
        light.setColor(color);
        light.setColor(color.r, color.g, color.b, alpha);
        light.setSoft(soft);
        light.setXray(xRay);
    }

    @Override
    public void update() {

    }

    @Override
    public void dispose() {
        light.dispose();
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Vector2 position) {
        this.light.setPosition(position);
    }
}
