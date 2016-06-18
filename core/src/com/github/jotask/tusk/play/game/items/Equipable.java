package com.github.jotask.tusk.play.game.items;

import com.github.jotask.tusk.play.game.Inventory;

/**
 * Itemable
 *
 * @author Jose Vives Iznardo
 * @since 05/06/2016
 */
public interface Equipable {

    void pickUp(Inventory inventory);

    void drop();

}
