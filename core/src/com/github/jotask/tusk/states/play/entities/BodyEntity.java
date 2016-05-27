package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class BodyEntity extends Entity{

    protected World world;
    protected Body body;

    public BodyEntity(World world, Body body) {
        this.body = body;
        this.world = world;
    }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) { }

    @Override
    public void dispose() {
        super.dispose();
        this.world.destroyBody(this.body);
    }

    public float getAngleFromThis(Vector2 other){
        return (float)Math.toDegrees(Math.atan2(other.y - this.getPosition().y, other.x - this.getPosition().x));
    }

    public Body getBody() { return this.body; }
    public World getWorld() { return this.world; }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }

}
