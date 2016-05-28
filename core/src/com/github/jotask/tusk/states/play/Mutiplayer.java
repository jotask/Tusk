package com.github.jotask.tusk.states.play;

import com.github.jotask.tusk.engine.online.client.TuskClient;

import java.io.IOException;

/**
 * Mutiplayer
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Mutiplayer extends Play{

    private TuskClient client;

    @Override
    public void init() {
        super.init();
        try {
            this.client = new TuskClient(this);
        } catch (IOException e) {
            System.err.println("Impossible connect to the server");
        }
    }

    @Override
    public void update () {
        super.update();
        if (this.client != null) this.client.sendPlayer(this.getPlayer());
    }

    @Override
    public void dispose() {
        super.dispose();
        if(this.client != null) this.client.dispose();
    }

    public TuskClient getClient() {
        return client;
    }

}