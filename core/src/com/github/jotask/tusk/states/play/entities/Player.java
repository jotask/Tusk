package com.github.jotask.tusk.states.play.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.states.play.world.Mundo;
import com.github.jotask.tusk.util.Constants;

public class Player extends Entity{

    private final float SPEED = 10;
    private final float JUMP = 10000;

    private OrthographicCamera camera;

    protected Body body;
    protected Fixture fixture;

    private Texture texture;
    private TextureRegion[] walkFrames;
    private TextureRegion currentFrame;
    private Animation walkAnimation;

    private float stateTime;

    public Player(Mundo world, OrthographicCamera camera) {
        this.camera = camera;

        final int width = 15;
        final int height = 20;
        // ANIMATION
        {
            this.texture = new Texture(Gdx.files.internal("characters.png"));
            walkFrames = new TextureRegion[2];
            walkFrames[0] = new TextureRegion(texture, 9, 12, width, height);
            walkFrames[1] = new TextureRegion(texture, 42, 12, width, height);
            walkAnimation = new Animation(0.25f, walkFrames);
        }

        {
            // CREATE BODY
            BodyDef bd = new BodyDef();
            bd.position.set(world.getLevel().getPlayerSpawn());
            bd.type = BodyDef.BodyType.DynamicBody;

            PolygonShape shape = new PolygonShape();
            shape.setAsBox((width / 2f), (height / 2f));

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;

            body = world.getWorld().createBody(bd);
            fixture = body.createFixture(fd);

            shape.dispose();
        }

    }

    @Override
    public void update() {

        {
            // Update animation
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
//            currentFrame = walkFrames[1];
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            body.applyLinearImpulse(new Vector2(-SPEED, 0), body.getLocalCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            body.applyLinearImpulse(new Vector2(SPEED, 0), body.getLocalCenter(), true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            body.applyLinearImpulse(new Vector2(0, SPEED), body.getLocalCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            body.applyLinearImpulse(new Vector2(0, -SPEED), body.getLocalCenter(), true);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            body.applyForceToCenter(new Vector2(0, JUMP), true);
        }

        Vector2 v = body.getPosition();
        camera.position.set(v.x, v.y, 10);
        camera.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        sb.draw(currentFrame,
                body.getPosition().x - (currentFrame.getRegionWidth() / 2),
                body.getPosition().y - (currentFrame.getRegionHeight() / 2)
        );
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
//        sr.box(body.getPosition().x - (currentFrame.getRegionWidth() / 2),
//                body.getPosition().y - (currentFrame.getRegionHeight() / 2),
//                0,
//                currentFrame.getRegionWidth(),
//                currentFrame.getRegionHeight(),
//                0);
    }

    public void spawn(Vector2 playerSpawn) {
        this.body.getPosition().set(playerSpawn);
    }
}
