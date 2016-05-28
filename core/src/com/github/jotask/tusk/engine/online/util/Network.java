package com.github.jotask.tusk.engine.online.util;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.LinkedList;

/**
 * Created by Jota on 27/05/2016.
 */
public abstract class Network {

    public static final int TCP_PORT = 25565;
    public static final int UDP_PORT = 25566;
    public static final int TIMEOUT = 5000;

    public static final String HOST = "localhost";

    public static void register(EndPoint endPoint){
        final Kryo kryo = endPoint.getKryo();
        kryo.register(Login.class);
        kryo.register(Character.class);
        kryo.register(LinkedList.class);
        kryo.register(Vector2.class);
        kryo.register(Characters.class);
        kryo.register(Lantern.class);
        kryo.register(Disconnected.class);
    }

    public static class Login{
        public int id;
        public String name;
        public boolean logged;
    }

    public static class Lantern{
        public boolean on;
    }

    public static class Character{
        public int id;
        public String name;
        public float angle;
        public Vector2 position;
        public Lantern lantern;
    }

    public static class Characters{
        public LinkedList<Character> characters;
    }

    public static class Disconnected{
        public int id;
    }

}
