package com.github.jotask.tusk.play.game;

import com.badlogic.gdx.Gdx;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.online.client.TuskClient;

import java.io.IOException;

/**
 * Mutiplayer
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Mutiplayer extends Game {

    private TuskClient client;

    public Mutiplayer(final Tusk tusk) { super(tusk); }

    @Override
    public void init() {
        super.init();
        try {
            this.client = new TuskClient(this);
        } catch (IOException e) {
            System.err.println("Impossible connect to the server");
            Gdx.app.exit();
        }
    }

    @Override
    public void update () {
        super.update();
        this.client.sendPlayer(this.getPlayer());
    }

    @Override
    public void dispose() {
        super.dispose();
        this.client.dispose();
    }

    public TuskClient getClient() {
        return client;
    }

}