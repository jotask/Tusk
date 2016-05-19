package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.states.play.world.Mundo;
import com.github.jotask.tusk.util.Util;

public class BodyEntity extends Entity{

    protected World world;
    protected Body body;

    public BodyEntity(TypeEntity type, Mundo world, Vector2 position, Vector2 size) {
        if(type != TypeEntity.BULLET)
            this.world = world.getWorld();
            this.body = Factory.Body.createBody(type, world, position, size);
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {
        Vector2 p = body.getPosition();
        Vector2 s = new Vector2(Util.Pixel.toMeter(Player.width) * 2, Util.Pixel.toMeter(Player.height) * 2);
//        sr.box( p.x - (s.x / 2), p.y - (s.y / 2), 0, s.x, s.y, 0 );
    }

    @Override
    public void dispose() {
        super.dispose();
        this.world.destroyBody(this.body);
    }

    public Body getBody() { return this.body; }
    public Vector2 getPosition(){
        return this.body.getPosition();
    }

}
