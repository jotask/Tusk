package com.github.jotask.tusk.engine.online.client;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.github.jotask.tusk.engine.online.util.Network;

/**
 * Created by Jota on 27/05/2016.
 */
public class ClientListener extends Listener {

    private final TuskClient client;

    public ClientListener(TuskClient client) {
        this.client = client;
    }

    @Override
    public void connected(Connection connection) {
        Network.Login login = new Network.Login();
        login.name = "client_" + System.nanoTime() % 100;
        client.getClient().sendTCP(login);
    }

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof Network.Login){
            Network.Login login = (Network.Login) object;
            if(login.logged){
                Network.Character character = client.getCharacter();
                character.id = connection.getID();
                character.name = login.name;
                character.position = client.getGame().getPlayer().getBody().getPosition();
                Network.Lantern lantern = new Network.Lantern();
                lantern.on = client.getGame().getPlayer().getLantern().isOn();
                character.lantern = lantern;
                client.getClient().sendTCP(character);
            }
        }else if(object instanceof Network.Character){
            client.receivedCharacter((Network.Character) object);
        }else if(object instanceof Network.Disconnected){
            client.disconnected((Network.Disconnected) object);
        }else if(object instanceof Network.Characters){
            client.receivedCharacters(((Network.Characters) object).characters);
        }
    }

    @Override
    public void disconnected(Connection connection) {

    }
}
