package com.github.jotask.tusk.states.play.world.enviorement;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class Environment {

    private boolean shadows = true;

    protected World world;
    protected RayHandler rayHandler;

    private Ambient ambient;

    public Environment(World world) {

        RayHandler.useDiffuseLight(true);
        this.rayHandler = new RayHandler(world);
        this.rayHandler.setShadows(shadows);

        ambient = new BasicAmbient(rayHandler);

    }

    public void update() {
        ambient.update();
    }

    public void render(SpriteBatch sb, OrthographicCamera camera) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    public void debug(ShapeRenderer sr) {

    }

    public void dispose(){
        rayHandler.dispose();
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }
}
