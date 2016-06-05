package com.github.jotask.tusk.engine.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.play.Play;
import com.github.jotask.tusk.play.entities.BodyEntity;
import com.github.jotask.tusk.play.entities.Enemy;
import com.github.jotask.tusk.play.entities.EntityManager;
import com.github.jotask.tusk.play.entities.bullet.Bullet;
import com.github.jotask.tusk.play.entities.player.Player;
import com.github.jotask.tusk.play.entities.player.PlayerIdle;
import com.github.jotask.tusk.play.world.Collisions;
import com.github.jotask.tusk.play.world.Mundo;
import com.github.jotask.tusk.play.world.environment.Environment;
import com.github.jotask.tusk.play.world.environment.lights.Fire;
import com.github.jotask.tusk.selectplayer.SelectPlayer;
import com.github.jotask.tusk.util.Util;

public final class Factory {

    static EntityManager manager = EntityManager.get();

    public static class Lights{

        public static Fire getFire(Environment environment, Vector2 position){
            Fire luz = new Fire(environment);
            luz.setPosition(position);
            return luz;
        }

    }

    public static class Entities {

        public static Player createPlayer(Play play) { return createPlayer(play, SelectPlayer.Players.DEFAULT); }

        public static Player createPlayer(Play play, SelectPlayer.Players playerType) {
            Body body = Bodies.createPlayer(play.getWorld(),
                    play.getWorld().getLevel().getPlayerSpawn(),
                    new Vector2(playerType.width, playerType.height));
            Player player = new Player(body);
            return player;

        }

        public static Enemy createEnemy(Play play, Vector2 position) {
            return createEnemy(play, position, Enemy.EnemyType.DEFAULT);
        }

        public static Enemy createEnemy(Play play, Vector2 position, Enemy.EnemyType type){
            Enemy enemy = new Enemy(Bodies.createEnemy(play.getWorld(), position, type.size));
            return enemy;
        }

        public static PlayerIdle createPlayerIdle(Play play, Network.Character character) {
            return createPlayerIdle(play, character, SelectPlayer.Players.DEFAULT);
        }

        public static PlayerIdle createPlayerIdle(Play play, Network.Character character, SelectPlayer.Players playerType) {

            Body body = Bodies.createPlayer(play.getWorld(),
                    play.getWorld().getLevel().getPlayerSpawn(),
                    new Vector2(playerType.width, playerType.height));

            PlayerIdle player = new PlayerIdle(body, character);
            manager.getPlayerIdle().add(player);
            return player;

        }

        public static Bullet createBullet(BodyEntity entity) {
            boolean isPlayer = ((entity instanceof Player) || (entity instanceof PlayerIdle));
            Body body = Bodies.createBullet(entity.getWorld(), entity.getPosition(), isPlayer);
            //float angle = entity.getAngleFromThis(Play.getInstance().getCamera().getMousePosInGameWorld());
            Bullet bullet = new Bullet(body, entity);
            boolean s = manager.getBullets().add(bullet);
            System.out.println(s);
            return bullet;
        }

        private static class Bodies {

            public static Body createPlayer(Mundo world, Vector2 position, Vector2 size) {

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
                fd.filter.categoryBits = Collisions.Filters.CATEGORY_PLAYER;
                fd.filter.maskBits = Collisions.Filters.MASK_PLAYER;
                fd.friction = 5f;
                fd.shape = shape;

                com.badlogic.gdx.physics.box2d.Body body = world.getWorld().createBody(bd);
                body.createFixture(fd);

                shape.dispose();

                return body;

            }

            public static Body createBullet(World world, Vector2 position, boolean isPlayer) {

                BodyDef bd = new BodyDef();
                bd.position.set(position.x, position.y);
                bd.type = BodyDef.BodyType.DynamicBody;


                CircleShape shape = new CircleShape();
                shape.setRadius(Bullet.RADIUS);

                FixtureDef fd = new FixtureDef();

                if (isPlayer) {
                    fd.filter.categoryBits = Collisions.Filters.CATEGORY_PLAYER;
                    fd.filter.maskBits = Collisions.Filters.MASK_PLAYER;
                } else {
                    fd.filter.categoryBits = Collisions.Filters.CATEGORY_ENEMY_BULLET;
                    fd.filter.maskBits = Collisions.Filters.MASK_MONSTER;
                }

                fd.friction = 0.25f;
                fd.shape = shape;
                fd.restitution = 0.01f;

                Body body = world.createBody(bd);
                body.createFixture(fd);

                shape.dispose();

                return body;
            }

            public static Body createEnemy(Mundo world, Vector2 position, Vector2 size) {
                return createPlayer(world, position, size);
            }

        }

    }

}
