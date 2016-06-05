package com.github.jotask.tusk.play.items;

import com.github.jotask.tusk.play.Inventory;

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
