package com.github.jotask.tusk.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class BodyEntity extends Entity {

    protected Body body;

    public BodyEntity(Body body) {
        super();
        this.body = body;
        this.body.setUserData(this);
    }

    @Override
    public void update() { }

    @Override
    public void render(SpriteBatch sb) { }

    @Override
    public void debug(ShapeRenderer sr) { }

    @Override
    public void dispose() {
        this.body.getWorld().destroyBody(this.body);
    }

    public float getAngleFromThis(Vector2 other){
        return (float)Math.toDegrees(Math.atan2(other.y - this.getPosition().y, other.x - this.getPosition().x));
    }

    public Body getBody() { return this.body; }

    public World getWorld() { return this.getBody().getWorld(); }

    public Vector2 getPosition(){ return this.body.getPosition(); }

}
