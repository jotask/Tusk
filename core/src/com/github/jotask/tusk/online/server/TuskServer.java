package com.github.jotask.tusk.online.server;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Server;
import com.github.jotask.tusk.online.util.AvlTree;
import com.github.jotask.tusk.online.util.Network;

import java.io.IOException;

/**
 * Created by Jota on 27/05/2016.
 */
public class TuskServer implements Disposable {

    private ServerGui gui;

    private final AvlTree<Network.Character> avlTree;

    private final Server server;

    private final ServerUpdater serverUpdater;
    private final Thread threadUpdater;

    public TuskServer() throws IOException {
        {
            this.gui = new ServerGui(this);
            this.avlTree = new AvlTree<Network.Character>();
        }
        {
            this.server = new Server();
            Network.register(this.server);

            ServerListener listener = new ServerListener(this);
            this.server.addListener(listener);

            this.server.bind(Network.TCP_PORT, Network.UDP_PORT);
            this.server.start();

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

    public AvlTree<Network.Character> getAvlTree() {
        return avlTree;
    }

    public Server getServer() {
        return server;
    }

    public static void main(String[] args) {
        try {
            new TuskServer();
        } catch (IOException e) {
            System.err.println("Impossible initiate the server. Try other ports!");
        }
    }

    public synchronized void receivedCharacter(Network.Character character) {
        avlTree.insert(character.id, character);
        gui.updateLabel(character);
    }

    public synchronized void disconnected(int id){
        avlTree.delete(id);
        gui.removeLabel(id);
    }

}
