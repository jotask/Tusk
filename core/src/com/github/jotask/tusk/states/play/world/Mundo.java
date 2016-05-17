package com.github.jotask.tusk.states.play.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.states.play.Play;

public class Mundo {

    private World world;
    private Box2DDebugRenderer debug;

    private Level level;

    public Mundo() {
        this.world = new World(new Vector2(0f,-9.8f), false);
        this.debug = new Box2DDebugRenderer();

        this.level = new Level(this.world);
    }

    public void update(){
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public void render(OrthographicCamera camera){ level.render(camera); }

    public void debug(Matrix4 matrix){
        this.debug.render(this.world, matrix);
    }

    public void dispose(){
        this.debug.dispose();
        this.world.dispose();
        this.level.dispose();
    }

    public World getWorld() {
        return world;
    }
    public Level getLevel(){ return level; }
}
