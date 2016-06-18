package com.github.jotask.tusk.play.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.play.game.Game;
import com.github.jotask.tusk.play.game.entities.player.Player;

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

        this.player = Game.getInstance().getPlayer();

        chunks = new LinkedList<Chunk>();

        int xxx = 0;
        int yyy = 0;

        for(int x = xxx; x < 8; x++){
            for(int y = yyy; y < 8; y++){
                chunks.add(new Chunk(x, y));
            }
        }

    }

    public void update(){

        int x = (int) (player.getPosition().x / Chunk.SIZE);
        int y = (int) (player.getPosition().y / Chunk.SIZE);

        int r = 1;

        for(Chunk c: chunks){

            if(c.isLoaded) c.isLoaded = false;
            if(c.isLazy) c.isLazy = false;

            if(c.x == x && c.y == y ){
                c.isLoaded = true;
                continue;
            }

            int diffX = Math.abs(c.x - x);
            int diffY = Math.abs(c.y - y);

            if(diffX <= r && diffY <= r) c.isLazy = true;

        }

    }

    public void render(SpriteBatch sb){ for(Chunk c: chunks) if(c.isLazy || c.isLoaded) c.render(sb); }

    public void debug(ShapeRenderer sr){ for(Chunk c: chunks) if(c.isLazy || c.isLoaded) c.debug(sr); }

    public void dispose(){ for(Chunk c: chunks) c.dispose(); }

}

class Chunk{

    public boolean isLoaded;
    public boolean isLazy;

    public static final float SIZE = 16 / 4f;

    final int x, y;

    public Chunk(int x, int y) {
        this.x = x;
        this.y = y;
        isLoaded = false;
    }

    public void update(){ }

    public void render(SpriteBatch sb){ }

    public void debug(ShapeRenderer sr){
        sr.box(x * SIZE, y * SIZE, 0, SIZE, SIZE, 0);
        if(isLazy){
            float offset = 0.1f;
            sr.box(x * SIZE + offset, y * SIZE +  offset, 0, SIZE - (offset * 2), SIZE - (offset * 2), 0);
        }else if(isLoaded){
            float offset = 0.5f;
            sr.box(x * SIZE + offset, y * SIZE +  offset, 0, SIZE - (offset * 2), SIZE - (offset * 2), 0);
        }
    }

    public void dispose(){ }

}