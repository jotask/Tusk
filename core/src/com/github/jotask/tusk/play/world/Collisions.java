package com.github.jotask.tusk.play.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class Collisions implements ContactListener {

    public class Filters{

        public static final short CATEGORY_SCENERY = 0x0001;

        public static final short CATEGORY_PLAYER = 0x0002;
        public static final short CATEGORY_PLAYER_BULLET = 0x0004;

        public static final short CATEGORY_ENEMY = 0x0008;
        public static final  short CATEGORY_ENEMY_BULLET = 0x0016;

        public static final short MASK_PLAYER = ~CATEGORY_PLAYER; // or ~CATEGORY_PLAYER
        public static final short MASK_MONSTER = ~CATEGORY_ENEMY; // or ~CATEGORY_MONSTER
        public static final short MASK_SCENERY = -1;

    }

    @Override
    public void beginContact(Contact contact) {

        Object a = contact.getFixtureA().getBody().getUserData();
        Object b = contact.getFixtureB().getBody().getUserData();

        if(a == null || b == null)
            return;


        /*
        If contanct is player sensor contact with spawner
        activate spawner
         */

    }

    @Override public void endContact(Contact contact) {
        /*
        if contact is player sensor with player spawner
        deactive spawer
         */
    }
    @Override public void preSolve(Contact contact, Manifold oldManifold) { }
    @Override public void postSolve(Contact contact, ContactImpulse impulse) { }

}