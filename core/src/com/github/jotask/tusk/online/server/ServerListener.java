package com.github.jotask.tusk.online.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

/**
 * Created by Jota on 27/05/2016.
 */
public class ServerListener extends Listener {

    private final TuskServer tuskServer;

    public ServerListener(TuskServer tuskServer) {
        this.tuskServer = tuskServer;
    }

    @Override
    public void received(Connection connection, Object object) {

    }

    @Override
    public void disconnected(Connection connection) {

    }
}
