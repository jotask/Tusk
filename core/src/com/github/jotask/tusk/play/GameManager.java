package com.github.jotask.tusk.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.AbstractState;

/**
 * GameManager
 *
 * @author Jose Vives Iznardo
 * @since 17/06/2016
 */
public class GameManager extends AbstractState {

    public interface GameState{
        void init();
        void update();
        void render(SpriteBatch sb);
        void debug(ShapeRenderer sr);
        void dispose();
    }

    private GameState currentState;

    public enum Game { GAME, SHOP, DUNGEON }

    private Play play;

    public GameManager(Play play) {
        super(play.getTusk());
        this.play = play;
    }

    public void changeState(Game game){

        // TODO

//        GameState state;
//        switch (game){
//            case SHOP:
//                state = new Shop(play);
//                break;
//            case DUNGEON:
//                state = new Dungeon(play);
//                break;
//            case GAME:
//            default:
//                state = new com.github.jotask.dungeon.state.play.Game(play);
//        }
//
//        if(state == null)
//            throw new RuntimeException("Impossible! GameManager");
//
//        if(currentState != null)
//            this.currentState.dispose();
//
//        this.currentState = new LoadGame(play, state);
//
//        this.currentState.init();

    }

    public void finishLoading(GameManager.GameState loaded){
        this.currentState.dispose();
        this.currentState = loaded;
        this.currentState.init();
    }

    @Override
    public void update() { this.currentState.update(); }

    @Override
    public void render(SpriteBatch sb) { this.currentState.render(sb); }

    @Override
    public void debug(ShapeRenderer sr) { this.currentState.debug(sr); }

    @Override
    public void dispose() { this.currentState.dispose(); }

}