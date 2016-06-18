package com.github.jotask.tusk.play.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.play.game.Game;
import com.github.jotask.tusk.play.game.world.environment.Environment;
import com.github.jotask.tusk.play.game.world.environment.lights.Fire;
import com.github.jotask.tusk.util.Util;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.LinkedList;

public class Level implements Disposable {

    private TiledMap map;
    private TiledMapRenderer mapRenderer;

    private Vector2 playerSpawn;

    private LinkedList<Spawner> enemiesSpawn;

    private World world;

    public Level(World world) {
        this.world = world;
        map = new TmxMapLoader().load("level/new.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 0.0625f);

        this.enemiesSpawn = new LinkedList<Spawner>();
    }

    protected void init(){
        for(MapLayer layer: map.getLayers()) {
            if (layer.getName().equals("obstacles")) {
                for(MapObject obj: layer.getObjects()){
                    if(obj instanceof PolylineMapObject){
                        buildBodyFromPolyLine(world, ((PolylineMapObject) obj).getPolyline());
                    }else if(obj instanceof RectangleMapObject){
                        buildBodyFromRect(world, ((RectangleMapObject) obj).getRectangle());
                    }else if(obj instanceof EllipseMapObject){
                        buildBodyFromEllipse(world, ((EllipseMapObject) obj).getEllipse());
                    }else if(obj instanceof PolygonMapObject){
                        buildBodyFromPolygon(world, ((PolygonMapObject) obj).getPolygon());
                    }else{
                        System.err.println("object not detected");
                    }
                }
            }else if(layer.getName().equals("light")){
                Environment environment = Game.getInstance().getWorld().getEnviorment();
                for(MapObject obj : layer.getObjects()){
                    if(obj.getName().equals("fire")) {
                        Rectangle rect = this.getRectangle(obj);
                        Vector2 position = new Vector2(rect.x + (rect.width / 2f), rect.y + (rect.height / 2f));
                        Fire luz = Factory.Lights.getFire(environment, Util.Pixel.toMeter(position));
                        environment.addLight(luz);
                    }
                }

            }
        }
        knowPlayerSpawn();
    }

    private Rectangle getRectangle(MapObject obj){

        float w = Float.valueOf(obj.getProperties().get("width").toString());
        float h = Float.valueOf(obj.getProperties().get("height").toString());

        float x = Float.valueOf(obj.getProperties().get("x").toString());
        float y = Float.valueOf(obj.getProperties().get("y").toString());

        return new Rectangle(x, y, w, h);

    }

    private void buildBodyFromPolygon(World world, Polygon polygon) {

        Rectangle r = polygon.getBoundingRectangle();

        float[] vertices = polygon.getVertices();
        float[] finalVertices = new float[vertices.length];

        for(int i = 0; i < vertices.length; i++){
            float tmp = vertices[i];
            finalVertices[i] = Util.Pixel.toMeter(tmp - (r.width / 2f));
        }

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
        shape.set(finalVertices);

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fd);

        body.setUserData(new Ground(r));

        shape.dispose();

    }

    private void buildBodyFromEllipse(World world, Ellipse ellipse) {
        Vector2 position = new Vector2(ellipse.x, ellipse.y);
        position.x = Util.Pixel.toMeter(ellipse.x);
        position.y = Util.Pixel.toMeter(ellipse.y);

        float w = Util.Pixel.toMeter(ellipse.width);
        float h = Util.Pixel.toMeter(ellipse.height);
        Vector2 size = new Vector2(w, h);

        position.x += size.x / 2;
        position.y += size.y / 2;

        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.StaticBody;
        bd.position.set(position);

        CircleShape shape = new CircleShape();
        shape.setRadius(Util.Pixel.toMeter(ellipse.width / 2f));

        FixtureDef fd = new FixtureDef();
        fd.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fd);

        body.setUserData(new Ground(ellipse));

        shape.dispose();
    }

    private void buildBodyFromPolyLine(World world, Polyline polyline) {
        throw new NotImplementedException();
    }

    private void buildBodyFromRect(World world, Rectangle r){

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
        fd.filter.categoryBits = Collisions.Filters.CATEGORY_SCENERY;
        fd.filter.maskBits = Collisions.Filters.MASK_SCENERY;
        fd.shape = shape;

        Body body = world.createBody(bd);
        body.createFixture(fd);

        body.setUserData(new Ground(r));

        shape.dispose();

    }

    public void knowPlayerSpawn(){
        MapObjects spawn = map.getLayers().get("spawn").getObjects();
        for(int i = 0; i < spawn.getCount(); i++){
            MapObject obj = spawn.get(i);
            if(obj.getName().equals("player")) {
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

    public Vector2 getPlayerSpawn() {
        return playerSpawn;
    }

    @Override
    public void dispose() {
        this.map.dispose();
    }

    public class Ground{
        private Shape2D shape;

        public Ground(Shape2D shape) {
            this.shape = shape;
        }

        public Shape2D getShape() { return shape; }

    }

    private void createEnemiesSpawners(){
        Vector2 tmp = Util.Pixel.toMeter(new Vector2());
        enemiesSpawn.add(new Spawner(tmp));
    }

    public LinkedList<Vector2> getClosedSpawner(){
        LinkedList<Vector2> result = new LinkedList<Vector2>();
        for(Spawner s: enemiesSpawn) if(s.isActive) result.add(s.position);
        return result;
    }

    class Spawner{
        boolean isActive;
        final Vector2 position;

        public Spawner(Vector2 position) {
            this.position = position;
            this.isActive = false;
        }

    }

}