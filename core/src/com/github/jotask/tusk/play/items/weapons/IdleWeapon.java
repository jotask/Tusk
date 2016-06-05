package com.github.jotask.tusk.play.items.weapons;

import com.github.jotask.tusk.engine.online.util.Network;

/**
 * IdleWeapon
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class IdleWeapon extends Weapon {
    
    private final Network.Weapon weapon;

    public IdleWeapon() {
        this.weapon = new Network.Weapon();
        this.weapon.fire = 0;
    }

    public void setData(Network.Weapon data) {
        this.weapon.fire = data.fire;
    }

}