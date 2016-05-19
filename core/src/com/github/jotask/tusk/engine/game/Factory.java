package com.github.jotask.tusk.engine.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.github.jotask.tusk.states.play.entities.TypeEntity;
import com.github.jotask.tusk.states.play.world.Mundo;
import com.github.jotask.tusk.util.Util;

public final class Factory {

    public static class Body {

        public static com.badlogic.gdx.physics.box2d.Body createBody(TypeEntity type, Mundo world, Vector2 position, Vector2 size){
            switch (type){
                case PLAYER:
                    return Body.createPlayer(world, position, size);
                case BULLET:
                    return Body.createBullet(world, position, size);
                case ENEMY:
                    return Body.createEnemy(world, position, size);
                default:
                    throw new RuntimeException("impossible");
            }

        }

        private static com.badlogic.gdx.physics.box2d.Body createPlayer(Mundo world, Vector2 position, Vector2 size){

            float x = Util.Pixel.toMeter(position.x);
            float y = Util.Pixel.toMeter(position.y);

            BodyDef bd = new BodyDef();
            bd.position.set(x, y);
            bd.fixedRotation = true;
            bd.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();

            float w = Util.Pixel.toMeter(size.x);
            float h = Util.Pixel.toMeter(size.y);
            shape.setAsBox(w / 2, h / 2f);

            FixtureDef fd = new FixtureDef();
            fd.friction = 5f;
            fd.density = 1f;
            fd.restitution = 0.25f;
            fd.shape = shape;

            com.badlogic.gdx.physics.box2d.Body body = world.getWorld().createBody(bd);
            body.createFixture(fd);

            shape.dispose();

            return body;

        }

        private static com.badlogic.gdx.physics.box2d.Body createBullet(Mundo world, Vector2 position, Vector2 size){
            return createPlayer(world, position, size);
        }

        private static com.badlogic.gdx.physics.box2d.Body createEnemy(Mundo world, Vector2 position, Vector2 size){
            return createBullet(world, position, size);
        }

    }

}
