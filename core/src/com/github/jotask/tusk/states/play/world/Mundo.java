package com.github.jotask.tusk.states.play.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.world.enviorement.Environment;

public class Mundo {

    private World world;
    private Box2DDebugRenderer debug;

    private Play state;

    private Level level;

    private Environment enviorment;

    public Mundo(Play state) {
        this.state = state;
        this.world = new World( new Vector2(0f,0f), false);
        world.setContactListener(new Collisions());
        this.debug = new Box2DDebugRenderer();

        this.level = new Level(this.world);

        this.enviorment = new Environment(world);

        createGround();

    }

    private void createGround(){
        float width = 1000;
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(0,0);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, 10);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        world.createBody(bd);
        shape.dispose();

    }

    public void update(){
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        enviorment.update();
    }

    public void render(SpriteBatch sb, OrthographicCamera camera){
        level.render(camera);
        enviorment.render(sb, state.getCamera());
    }

    public void debug(ShapeRenderer sr, Matrix4 matrix){
        this.debug.render(this.world, matrix);
        enviorment.debug(sr);
    }

    public void dispose(){
        this.debug.dispose();
        this.world.dispose();
        this.level.dispose();
        enviorment.dispose();
    }

    public World getWorld() {
        return world;
    }
    public Level getLevel() { return level; }

    public Environment getEnviorment() {
        return enviorment;
    }
}
