package com.github.jotask.tusk.online.server;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.github.jotask.tusk.online.util.Network;

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
        if(object instanceof Network.Login){
            Network.Login login = (Network.Login) object;
            if(isValid(login.name)){
                login.id = connection.getID();
                login.logged = true;
                tuskServer.getServer().sendToTCP(connection.getID(), login);
            }
        }else if(object instanceof Network.Character){
            tuskServer.receivedCharacter((Network.Character) object);
        }
    }

    @Override
    public void disconnected(Connection connection) {
        Network.Disconnected disconnected = new Network.Disconnected();
        disconnected.id = connection.getID();
        tuskServer.getAvlTree().delete(connection.getID());
        tuskServer.getServer().sendToAllExceptTCP(disconnected.id, disconnected);
    }

    private boolean isValid(String name){ return true; }

}
