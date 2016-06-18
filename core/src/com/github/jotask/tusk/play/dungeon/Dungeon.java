package com.github.jotask.tusk.play.dungeon;

import com.github.jotask.tusk.play.AbstractGameState;
import com.github.jotask.tusk.play.Play;

/**
 * Dungeon
 *
 * @author Jose Vives Iznardo
 * @since 18/06/2016
 */
public class Dungeon extends AbstractGameState {

    public Dungeon(Play play) {
        super(play);
    }

    @Override
    public void init() {
        System.out.println("Play.dungeon");
    }
}
