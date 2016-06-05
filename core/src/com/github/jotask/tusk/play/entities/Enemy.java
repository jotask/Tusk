package com.github.jotask.tusk.play.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Enemy
 *
 * @author Jose Vives Iznardo
 * @since 05/06/2016
 */
public class Enemy extends BodyEntity {

    public enum EnemyType{

        DEFAULT (new Vector2(16f, 16f));

        public final Vector2 size;

        EnemyType(final Vector2 size) {
            this.size = size;
        }
    }

    public Enemy(Body body) {
        super(body);
    }

    @Override
    public boolean toDestroy() {
        return false;
    }

}
