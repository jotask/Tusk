package com.github.jotask.tusk.play.game.world.Dungeon;

/**
 * Room
 *
 * @author Jose Vives Iznardo
 * @since 08/06/2016
 */
public class Room {

    private final long seed;
    private final float WIDTH, HEIGHT;

    public Room(final long seed) {
        this.seed = seed;
        WIDTH = 10f;
        HEIGHT = 10f;
    }

}