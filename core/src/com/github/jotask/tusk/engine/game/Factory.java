package com.github.jotask.tusk.engine.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.entities.bullet.Bullet;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.states.play.world.Mundo;
import com.github.jotask.tusk.states.selectplayer.SelectPlayer;
import com.github.jotask.tusk.util.Util;

public final class Factory {

    public static Player createPlayer(Play play){ return createPlayer(play, SelectPlayer.Players.DEFAULT); }
    public static Player createPlayer(Play play, SelectPlayer.Players playerType){

        com.badlogic.gdx.physics.box2d.Body body = Body.createPlayer(play.getWorld(),
                play.getWorld().getLevel().getPlayerSpawn(),
                new Vector2(playerType.width, playerType.height));

        Player player = new Player(play.getWorld().getWorld(), body);
        return player;

    }

    public static Bullet createBullet(BodyEntity entity){
        com.badlogic.gdx.physics.box2d.Body body = Body.createBullet(entity.getWorld(), entity.getPosition(), new Vector2(1f, 0.5f));
        Bullet bullet = new Bullet(entity.getWorld(), body);
        return bullet;
    }

    public static class Body {

        public static com.badlogic.gdx.physics.box2d.Body createPlayer(Mundo world, Vector2 position, Vector2 size){

            float x = Util.Pixel.toMeter(position.x);
            float y = Util.Pixel.toMeter(position.y);

            BodyDef bd = new BodyDef();
            bd.position.set(x, y);
            bd.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();

            float w = Util.Pixel.toMeter(size.x);
            float h = Util.Pixel.toMeter(size.y);
            shape.setAsBox(w / 2, h / 2f);

            FixtureDef fd = new FixtureDef();
            fd.friction = 5f;
            fd.shape = shape;

            com.badlogic.gdx.physics.box2d.Body body = world.getWorld().createBody(bd);
            body.createFixture(fd);

            shape.dispose();

            return body;

        }

        public static com.badlogic.gdx.physics.box2d.Body createBullet(World world, Vector2 position, Vector2 size){

            BodyDef bd = new BodyDef();
            bd.position.set(position.x, position.y);
            bd.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();

            float w = Util.Pixel.toMeter(size.x);
            float h = Util.Pixel.toMeter(size.y);
            shape.setAsBox(w / 2, h / 2f);

            FixtureDef fd = new FixtureDef();
            fd.friction = 5f;
            fd.shape = shape;
            fd.restitution = 0.05f;

            com.badlogic.gdx.physics.box2d.Body body = world.createBody(bd);
            body.createFixture(fd);

            shape.dispose();

            return body;
        }

        public static com.badlogic.gdx.physics.box2d.Body createEnemy(Mundo world, Vector2 position, Vector2 size){
            return createPlayer(world, position, size);
        }

    }

}