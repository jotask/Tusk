package com.github.jotask.tusk.play.game.world.Dungeon;

import com.github.jotask.tusk.engine.game.Factory;

import java.util.LinkedList;

/**
 * Dungeon
 *
 * @author Jose Vives Iznardo
 * @since 08/06/2016
 */
public class Dungeon {

    public final long SEED;
    public final int NUMBER_ROOMS;

    private LinkedList<Room> rooms;

    public Dungeon(DungeonConfig cfg) {
        this.SEED = cfg.seed;
        this.NUMBER_ROOMS = cfg.number_rooms;

        this.rooms = new LinkedList<Room>();
        for(int i = 0; i < NUMBER_ROOMS; i++){
            // TODO
            Room r = Factory.DungeonFactory.generateRoom(0);
            this.rooms.add(r);
        }

    }

}
