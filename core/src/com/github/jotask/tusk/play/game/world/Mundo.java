package com.github.jotask.tusk.play.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.play.game.Game;
import com.github.jotask.tusk.play.game.world.environment.Environment;

public class Mundo {

    private World world;
    private Box2DDebugRenderer debug;

    private Level level;

    private Environment environment;

    public Mundo() {
        this.world = new World( new Vector2(0f,0f), false);
        this.world.setContactListener(new Collisions());

        this.debug = new Box2DDebugRenderer();

        this.environment = new Environment(this.world);

        this.level = new Level(this.world);

    }

    public void init(){
        this.level.init();
        this.createGround();
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
        this.environment.update();
    }

    public void render(SpriteBatch sb, OrthographicCamera camera){
        this.level.render(camera);
    }

    public void postRender(SpriteBatch sb){
        this.environment.render(sb, Game.getInstance().getCamera());
    }

    public void debug(ShapeRenderer sr, Matrix4 matrix){
        this.debug.render(this.world, matrix);
        this.environment.debug(sr);
    }

    public void dispose(){
        this.debug.dispose();
        this.world.dispose();
        this.level.dispose();
        this.environment.dispose();
    }

    public World getWorld() {
        return world;
    }
    public Level getLevel() { return level; }

    public Environment getEnviorment() { return environment; }
}
