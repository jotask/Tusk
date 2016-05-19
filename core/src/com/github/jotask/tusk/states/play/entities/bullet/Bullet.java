package com.github.jotask.tusk.states.play.entities.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.engine.game.Timer;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.util.Util;

public class Bullet extends BodyEntity {

    private float SPEED = 100f;
    private final Timer timer;

    private TextureRegion textureRegion;

    public Bullet(World world, Body body) {
        super(world, body);
        this.timer = new Timer(1f);
        this.body.setLinearVelocity(SPEED, 0);

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.BULLET_TEXTURE);
        this.textureRegion = new TextureRegion(texture, 16, 20, 7, 3);

    }

    public boolean isDead(){
        return timer.isFinished();
    }

    @Override
    public void render(SpriteBatch sb) {
        Util.Render.render(sb, textureRegion, body);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        Util.Render.debug(sr, textureRegion, body);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
