package com.github.jotask.tusk.online.server;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Server;
import com.github.jotask.tusk.online.util.Network;

import java.io.IOException;

/**
 * Created by Jota on 27/05/2016.
 */
public class TuskServer implements Disposable {

    private ServerGui gui;

    private final Server server;
    private final Thread threadServer;

    private final ServerUpdater serverUpdater;
    private final Thread threadUpdater;

    public TuskServer() throws IOException {
        {
            this.gui = new ServerGui(this);
        }
        {
            this.server = new Server();
            Network.register(this.server);

            ServerListener listener = new ServerListener(this);
            this.server.addListener(listener);

            this.server.bind(Network.TCP_PORT, Network.UDP_PORT);

            threadServer = new Thread(this.server);
            threadServer.start();

        }
        {
            this.serverUpdater = new ServerUpdater(this);
            this.threadUpdater = new Thread(this.serverUpdater);
            this.threadUpdater.start();
        }

    }

    @Override
    public void dispose() {
        try {
            serverUpdater.stop();
            threadUpdater.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        server.stop();

    }

    public static void main(String[] args) {
        try {
            new TuskServer();
        } catch (IOException e) {
            System.err.println("Impossible initiate the server. Try other ports!");
        }
    }

}
