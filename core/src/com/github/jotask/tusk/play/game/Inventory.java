package com.github.jotask.tusk.play.game;

import com.github.jotask.tusk.play.game.entities.BodyEntity;
import com.github.jotask.tusk.play.game.entities.player.Lantern;
import com.github.jotask.tusk.play.game.items.Item;
import com.github.jotask.tusk.play.game.items.weapons.Weapon;

import java.util.LinkedList;

/**
 * Inventory
 *
 * @author Jose Vives Iznardo
 * @since 03/06/2016
 */
public class Inventory {

    private Weapon weapon;

    private Lantern lantern;

    private final BodyEntity owner;

    private final int MAX_ITEMS = 3;
    private LinkedList<Item> items;

    public Inventory(BodyEntity owner) {
        this.owner = owner;
        this.items = new LinkedList<Item>();
    }

    public boolean addItem(Item item){
        if(this.items.size() < MAX_ITEMS){
            items.add(item);
            return true;
        }
        return false;
    }

    public void update(){
        if(getLantern() != null) this.lantern.update();
        for(Item i: items){
            i.update();
        }
    }

    public Weapon getWeapon() { return this.weapon; }
    public Lantern getLantern(){ return this.lantern; }

    public void equipWeapon(Weapon weapon){
        if(weapon != null) weapon.dispose();
        this.weapon = weapon;
    }

    public void equipLantern(Lantern lantern){
        if(this.lantern != null) this.lantern.dispose();
        this.lantern = lantern;
    }

    public BodyEntity getOwner(){ return this.owner; }

}
