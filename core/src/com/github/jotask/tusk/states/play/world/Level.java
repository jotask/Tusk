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

public class Level implements Disposable{

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    private Vector2 playerSpawn;

    public Level(World world) {
        map = new TmxMapLoader().load("level/map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
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
                playerSpawn= new Vector2(rectangle.x, rectangle.y);
            }
        }
    }

    public void render(OrthographicCamera camera){
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    private Body buildBodyFromRect(World w, Rectangle r){

        float x = r.x + (r.getWidth() / 2);
        float y = r.y + (r.getHeight() / 2);

        Vector2 position = new Vector2(x, y);

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(r.getWidth() / 2, r.getHeight() / 2);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Body body = w.createBody(bd);
        body.createFixture(fd);

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
}
