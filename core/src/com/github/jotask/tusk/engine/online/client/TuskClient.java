package com.github.jotask.tusk.engine.online.client;

import com.badlogic.gdx.utils.Disposable;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Listener;
import com.github.jotask.tusk.engine.game.Factory;
import com.github.jotask.tusk.engine.online.util.AvlTree;
import com.github.jotask.tusk.engine.online.util.Network;
import com.github.jotask.tusk.play.Play;
import com.github.jotask.tusk.play.entities.player.Player;
import com.github.jotask.tusk.play.entities.player.PlayerIdle;

import java.io.IOException;
import java.util.LinkedList;

/**
 * TuskClient
 *
 * @author Jose Vives Iznardo
 * @since 27/05/2016
 */
public class TuskClient implements Disposable{

    private final Play game;

    private final Network.Character character;

    private final Client client;

    private final AvlTree<PlayerIdle> onlinePlayers;

    public TuskClient(Play game) throws IOException {

        this.game = game;
        this.onlinePlayers = new AvlTree<PlayerIdle>();
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

    synchronized void receivedCharacters(LinkedList<Network.Character> characters) {
        for(Network.Character c: characters){
            this.receivedCharacter(c);
        }
    }

    synchronized void receivedCharacter(Network.Character character){
        if(this.character.id == character.id) return;
        PlayerIdle playerIdle = this.onlinePlayers.exist(character.id);
        if(playerIdle == null){
            playerIdle = Factory.Players.createPlayerIdle(game, character);
        }
        playerIdle.setData(character);
        this.onlinePlayers.insert(character.id, playerIdle);
    }

    public void sendPlayer(Player player){
        Network.Character character = this.getCharacter();
        character.position = player.getBody().getPosition();
        character.angle = player.getBody().getAngle();

        if(character.lantern == null){
            character.lantern = new Network.Lantern();
        }

        character.lantern.on = player.getInventory().getLantern().isOn();
        character.lantern.angle = player.getInventory().getLantern().getAngle();

        if(character.weapon == null){
            character.weapon = new Network.Weapon();
        }

        character.weapon.fire = player.getInventory().getWeapon().getNeedsToFire();

        this.getClient().sendUDP(character);
    }

    synchronized void disconnected(Network.Disconnected disconnected){
        final PlayerIdle exist = this.onlinePlayers.exist(disconnected.id);
        exist.setDisconnected(true);
        this.onlinePlayers.delete(disconnected.id);
    }

    public Client getClient() { return client; }
    public Network.Character getCharacter() { return character; }

    public AvlTree<PlayerIdle> getOnlinePlayers() { return onlinePlayers; }

    public Play getGame(){ return this.game; }

    @Override
    public void dispose() {
        this.client.stop();
    }

    public static void main(String[] args) {
        new Client();
    }

}
