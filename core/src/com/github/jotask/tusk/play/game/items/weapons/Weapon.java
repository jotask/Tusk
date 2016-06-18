package com.github.jotask.tusk.play.game.items.weapons;

import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.play.game.Inventory;
import com.github.jotask.tusk.play.game.items.Equipable;
import com.github.jotask.tusk.play.game.items.Item;
import com.github.jotask.tusk.play.game.items.PriceValue;
import com.github.jotask.tusk.util.Timer;
import com.github.jotask.tusk.util.Util;

public abstract class Weapon extends Item implements Equipable {

    private Inventory owner;

    private final Timer timer;

    public Weapon() { this(1f); }
    public Weapon(float seconds){
        this(seconds, new PriceValue(0,0,0));
    }
    public Weapon(float seconds, PriceValue value) {
        super(value);
        this.timer = new Timer(Util.secondsToNano(seconds));
    }

    public boolean shot() {
        if(timer.isFinished()) {
            Factory.Bullets.createBullet(owner.getOwner());
            return true;
        }
        return false;
    }

    @Override
    public void pickUp(Inventory inventory) {
        inventory.equipWeapon(this);
        owner = inventory;
    }

    @Override
    public void drop() {
        owner = null;
    }

    public int getNeedsToFire() {
        return 0;
    }

}
