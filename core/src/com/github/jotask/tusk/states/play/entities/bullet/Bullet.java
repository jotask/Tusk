package com.github.jotask.tusk.states.play.entities.bullet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.entities.TypeEntity;
import com.github.jotask.tusk.states.play.world.Mundo;

public class Bullet extends BodyEntity {

    private Bullet(TypeEntity type, Mundo world, Vector2 position, Vector2 size) {
        super(type, world, position, size);

        if(this.body == null){
            return;
        }

    }

    @Override
    public void update() {
        if(this.body != null)
            super.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(this.body != null)
            super.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        if(this.body != null)
            super.debug(sr);
    }

    @Override
    public void dispose() {
        if(this.body != null)
            super.dispose();
    }

}
