package com.github.jotask.tusk.states.play.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.tusk.util.Util;

public class Level implements Disposable{

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    private Vector2 playerSpawn;

    public Level(World world) {
        map = new TmxMapLoader().load("level/test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 0.0625f);
        {
            MapObjects collisionsObjects = map.getLayers().get("obstacles").getObjects();
            for(int i = 0; i < collisionsObjects.getCount(); i++){
                MapObject obj = collisionsObjects.get(i);
                MapProperties props = obj.getProperties();
                float x = Float.valueOf(props.get("x").toString());
                float y = Float.valueOf(props.get("y").toString());
                float width = Float.valueOf(props.get("width").toString());
                float height = Float.valueOf(props.get("height").toString());
                Rectangle rectangle = new Rectangle(x, y, width, height);
                this.buildBodyFromRect(world, rectangle);

            }
        }
        {
            MapObjects spawn = map.getLayers().get("spawn").getObjects();
            for(int i = 0; i < spawn.getCount(); i++){
                MapObject obj = spawn.get(i);
                MapProperties props = obj.getProperties();
                float x = Float.valueOf(props.get("x").toString());
                float y = Float.valueOf(props.get("y").toString());
                Rectangle rectangle = new Rectangle(x, y, 1, 1);
                playerSpawn = new Vector2(rectangle.x, rectangle.y);
            }
        }
    }

    public void render(OrthographicCamera camera){
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    private Body buildBodyFromRect(World world, Rectangle r){

        Vector2 position = new Vector2(r.x, r.y);
        position.x = Util.Pixel.toMeter(r.x);
        position.y = Util.Pixel.toMeter(r.y);

        float w = Util.Pixel.toMeter(r.width);
        float h = Util.Pixel.toMeter(r.height);
        Vector2 size = new Vector2(w, h);

        position.x += size.x / 2;
        position.y += size.y / 2;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x / 2, size.y / 2);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fd);

        body.setUserData(new Ground(r));

        shape.dispose();

        return body;
    }

    public Vector2 getPlayerSpawn() {
        return playerSpawn;
    }

    @Override
    public void dispose() {
        this.map.dispose();
    }

    public class Ground{
        private Rectangle rect;

        public Ground(Rectangle rect) {
            this.rect = rect;
        }

        public Rectangle getRect() { return rect; }

    }

}