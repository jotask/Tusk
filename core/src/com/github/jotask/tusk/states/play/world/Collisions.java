package com.github.jotask.tusk.states.play.world;

import com.badlogic.gdx.physics.box2d.*;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.player.Player;

public class Collisions implements ContactListener{

    @Override
    public void beginContact(Contact contact) {

        Object a = contact.getFixtureA().getBody().getUserData();
        Object b = contact.getFixtureB().getBody().getUserData();

        if(a == null || b == null)
            return;

        if ((a instanceof Player && b instanceof Level.Ground) || (a instanceof Level.Ground && b instanceof Player)) {
            Player player;
            if(a instanceof Play){
                player = (Player) a;
            }else{
                player = (Player) b;
            }
            player.setCanJump(true);
        }

    }

    private boolean areColliding(Contact contact, Class c1, Class c2){

        Object a = contact.getFixtureA().getBody().getUserData();
        Object b = contact.getFixtureB().getBody().getUserData();

//        return ((a instanceof c1) || ());

        return false;

    }

    @Override public void endContact(Contact contact) { }
    @Override public void preSolve(Contact contact, Manifold oldManifold) { }
    @Override public void postSolve(Contact contact, ContactImpulse impulse) { }

}
