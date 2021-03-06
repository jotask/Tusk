package com.github.jotask.tusk.engine.online.server;

import com.github.jotask.tusk.engine.online.util.Network;

import java.util.LinkedList;

/**
 * Created by Jota on 27/05/2016.
 */
public class ServerUpdater implements Runnable {

    private final int TIME = 100;

    private boolean stop = false;

    private final TuskServer tuskServer;

    public ServerUpdater(TuskServer tuskServer) { this.tuskServer = tuskServer; }

    @Override
    public synchronized void run() {

        while (!stop){

            final LinkedList<Network.Character> allTree = tuskServer.getAvlTree().getAllTree();

            final Network.Characters characters = new Network.Characters();
            characters.characters = allTree;

            tuskServer.getServer().sendToAllUDP(characters);

            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void stop(){
        this.stop = true;
    }

}
