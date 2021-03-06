package com.github.jotask.tusk.selectplayer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.AbstractState;

import java.util.ArrayList;

public class SelectPlayer extends AbstractState {

    public enum Players {

        DEFAULT(1f, 15f);

        public final float width, height;

        Players(float width, float height) {
            this.height = height;
            this.width = width;
        }

    }

    private ArrayList<PlayerSelection> players;

    @Override
    public void init() {
        super.init();
        this.setBgColor(Color.BLACK);

        int size = Players.values().length;
        players = new ArrayList<PlayerSelection>(size);
        for(Players player: Players.values()){
            System.out.println(Players.valueOf(player.toString()));
            PlayerSelection p = new PlayerSelection(
                    player.name(),
                    null,
                    new Vector2(0,0),
                    new Vector2(10,10)
            );
            players.add(p);
        }

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        for(PlayerSelection p : players){
            p.render(sb);
        }
    }

    @Override
    public void debug(ShapeRenderer sr) {
        sr.setColor(Color.RED);
        super.debug(sr);
        for(PlayerSelection p : players){
            p.debug(sr);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    class PlayerSelection{

        private String name;
        private TextureRegion image;

        private Vector2 position;
        private Vector2 size;

        public PlayerSelection(String name, TextureRegion region, Vector2 position, Vector2 size) {
            this.name = name;
            this.image = region;
            this.position = position;
            this.size = size;
        }

        public void render(SpriteBatch sb){
//            sb.draw(image, position.x, position. y, size.x, size. y);
        }

        public void debug(ShapeRenderer sr){
            sr.box(position.x, position.y, 0, size.x, size.y, 0);
        }

    }

}
