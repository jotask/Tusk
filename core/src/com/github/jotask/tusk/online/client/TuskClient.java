package com.github.jotask.tusk.online.client;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.online.util.AvlTree;
import com.github.jotask.tusk.online.util.Network;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.player.PlayerIdle;

import java.io.IOException;

/**
 * Created by Jota on 27/05/2016.
 */
public class TuskClient implements Disposable{

    private final Play game;

    private final Network.Character character;

    private final Client client;

    class OnlineEntity{

        final Network.Character character;
        final PlayerIdle playerIdle;

        OnlineEntity(Network.Character character, PlayerIdle playerIdle) {
            this.character = character;
            this.playerIdle = playerIdle;
        }

    }

    private final AvlTree<OnlineEntity> avlTree;

    public TuskClient(Play game) throws IOException {

        this.game = game;
        this.avlTree = new AvlTree<OnlineEntity>();
        this.character = new Network.Character();

        {
            this.client = new Client();
            Network.register(this.client);

            Listener listener = new ClientListener(this);
            this.client.addListener(listener);

            this.client.start();

            this.client.connect(Network.TIMEOUT, Network.HOST, Network.TCP_PORT, Network.UDP_PORT);
        }
    }

    public void update(){
        // TODO update all entities
    }

    public synchronized void receivedCharacter(Network.Character character){
        System.out.println("recived " + character.name);
        if(this.character.id == character.id) return;
        OnlineEntity onlineEntity = this.avlTree.exist(character.id);
        if(onlineEntity == null){
            PlayerIdle playerIdle = Factory.createPlayerIdle(game);
            onlineEntity = new OnlineEntity(character, playerIdle);
        }
        this.avlTree.insert(character.id, onlineEntity);
    }

    public synchronized void disconnected(Network.Disconnected disconnected){
        this.avlTree.delete(disconnected.id);
    }

    public Client getClient() { return client; }
    public AvlTree<OnlineEntity> getAvlTree() { return avlTree; }
    public Network.Character getCharacter() { return character; }
    public Play getGame(){ return this.game; }

    @Override
    public void dispose() {
        this.client.stop();
    }

    public static void main(String[] args) {
        new Client();
    }

}
