package com.github.jotask.tusk.play.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.play.Play;
import com.github.jotask.tusk.play.entities.player.PlayerIdle;
import com.github.jotask.tusk.util.Timer;

/**
 * IdleWeapon
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class IdleWeapon implements Weapon {
    
    private final Network.Weapon weapon;

    private Timer timer;

    private PlayerIdle idle;

    public IdleWeapon(PlayerIdle idle) {
        this.idle = idle;
        this.timer = new Timer(1f);
        this.weapon = new Network.Weapon();
        this.weapon.fire = 0;
    }

    @Override
    public void shot() {
        /* Factory.Entities.createBullet(this.idle); */
    }

    public void update(){
        if(weapon.fire > 0) {
            weapon.fire--;
            shot();
        }
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {
        Vector2 p = Play.getInstance().getCamera().getMousePosInGameWorld();
        sr.line(idle.getPosition().x, idle.getPosition().y, p.x, p.y);
    }

    @Override
    public void dispose() {

    }

    public void setData(Network.Weapon data) {
        this.weapon.fire = data.fire;
    }
}