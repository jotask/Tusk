package com.github.jotask.tusk.play.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.play.Play;
import com.github.jotask.tusk.play.entities.player.Player;

import java.util.LinkedList;

/**
 * NewWorld
 *
 * @author Jose Vives Iznardo
 * @since 07/06/2016
 */
public class NewWorld {

    private final Player player;

    private LinkedList<Chunk> chunks;

    public NewWorld() {

        this.player = Play.getInstance().getPlayer();

        chunks = new LinkedList<Chunk>();
        chunks.add(new Chunk(0,0));
        chunks.add(new Chunk(1,0));
        chunks.add(new Chunk(0,1));
        chunks.add(new Chunk(1,1));

    }

    public void update(){

    }

    public void render(SpriteBatch sb){

    }

    public void debug(ShapeRenderer sr){
        for(Chunk c: chunks) c.debug(sr);
    }

    public void dispose(){
        for(Chunk c: chunks) c.dispose();
    }

}

class Chunk{

    final float SIZE = 16 / 4f;

    final float x, y;

    public Chunk(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update(){

    }

    public void render(SpriteBatch sb){

    }

    public void debug(ShapeRenderer sr){
        sr.box(x * SIZE, y * SIZE, 0, SIZE, SIZE, 0);
    }

    public void dispose(){

    }

}