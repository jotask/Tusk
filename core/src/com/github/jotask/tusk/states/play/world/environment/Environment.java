package com.github.jotask.tusk.states.play.world.environment;

import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.states.play.world.environment.lights.Luz;

import java.util.LinkedList;

public class Environment {

    private boolean shadows = true;

    protected World world;
    protected RayHandler rayHandler;

    private Ambient ambient;

    private LinkedList<Luz> lights;

    public Environment(World world) {

        RayHandler.useDiffuseLight(true);
        this.rayHandler = new RayHandler(world);
        this.rayHandler.setShadows(shadows);

        this.ambient = new DayCycle(rayHandler);

        lights = new LinkedList();

    }

    public void addLight(Luz light){
        lights.add(light);
    }

    public void update() {
        ambient.update();
        for(Luz l: lights) l.update();
    }

    public void render(SpriteBatch sb, OrthographicCamera camera) {
        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();
    }

    public void debug(ShapeRenderer sr) { }

    public void dispose(){
        rayHandler.dispose();
    }

    public RayHandler getRayHandler() {
        return rayHandler;
    }

}
