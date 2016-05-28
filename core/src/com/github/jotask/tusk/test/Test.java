package com.github.jotask.tusk.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;

public class Test extends AbstractState {

    protected World world;
    protected Box2DDebugRenderer renderer;

    private Body body;
    private Fixture fixture;

    private Body suelo;

    @Override
    public void init() {
        this.setBgColor(Color.BLACK);

        float width = 7.11f;
        float height = 4f;
        this.camera = new Camera(width, height);

        camera.translate(0,1);
        world = new World(new Vector2(0,-9.8f), true);
        renderer = new Box2DDebugRenderer();

        {
            BodyDef bd = new BodyDef();
            bd.position.set(0,5);
            bd.type = BodyDef.BodyType.DynamicBody;

            body = world.createBody(bd);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(0.5f,0.5f);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;

            fixture = body.createFixture(fd);

            shape.dispose();

        }

        {
            BodyDef bd = new BodyDef();
            bd.type = BodyDef.BodyType.StaticBody;
            bd.position.set(0,-1f);

            PolygonShape shape = new PolygonShape();
            shape.setAsBox(500, 1);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;

            suelo = world.createBody(bd);
            suelo.createFixture(fd);
        }


    }

    @Override
    public void update() {
        super.update();
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderer.render(world, camera.combined);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
    }

    @Override
    public void dispose() {
        world.destroyBody(body);
        super.dispose();
    }
}
