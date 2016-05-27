package com.github.jotask.tusk.engine.online.client;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.online.util.AvlTree;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.play.entities.player.Player;
import com.github.jotask.tusk.states.play.entities.player.PlayerIdle;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by Jota on 27/05/2016.
 */
public class TuskClient implements Disposable{

    private final Play game;

    private final Network.Character character;

    private final Client client;

    public void receivedCharacters(LinkedList<Network.Character> characters) {
        for(Network.Character c: characters){
            if(this.character.id != c.id) this.receivedCharacter(c);
        }
    }

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
        if(this.character.id == character.id) return;
        OnlineEntity onlineEntity = this.avlTree.exist(character.id);
        if(onlineEntity == null){
            PlayerIdle playerIdle = Factory.createPlayerIdle(game);
            onlineEntity = new OnlineEntity(character, playerIdle);
        }
        this.avlTree.insert(character.id, onlineEntity);
    }

    public void sendPlayer(Player player){
        Network.Character character = this.getCharacter();
        character.position = player.getBody().getPosition();
        this.getClient().sendUDP(character);
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
