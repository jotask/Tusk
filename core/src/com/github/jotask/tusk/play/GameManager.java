package com.github.jotask.tusk.play;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.engine.loader.LoadGame;
import com.github.jotask.tusk.play.dungeon.Dungeon;
import com.github.jotask.tusk.play.game.SinglePlayer;
import com.github.jotask.tusk.states.AbstractState;

/**
 * GameManager
 *
 * @author Jose Vives Iznardo
 * @since 17/06/2016
 */
public class GameManager extends AbstractState {

    private AbstractGameState currentState;

    public enum Game { GAME, DUNGEON }

    private Play play;

    public GameManager(Play play) {
        super(play.getTusk());
        this.play = play;
    }

    public void changeState(Game game){

        AbstractGameState state;
        switch (game){
            case DUNGEON:
                state = new Dungeon(play);
                break;
            case GAME:
            default:
                state = new SinglePlayer(play);
        }

        if(state == null)
            throw new RuntimeException("Impossible! GameManager");

        if(currentState != null)
            this.currentState.dispose();

        this.currentState = new LoadGame(play, state);

        this.currentState.init();

    }

    public void finishLoading(AbstractGameState loaded){
        this.currentState.dispose();
        this.currentState = loaded;
        this.currentState.init();
    }

    @Override
    public void preUpdate() { this.currentState.preUpdate(); }

    @Override
    public void update() { this.currentState.update(); }

    @Override
    public void postUpdate() { this.currentState.postUpdate(); }

    @Override
    public void preRender(SpriteBatch sb) { this.currentState.preRender(sb); }

    @Override
    public void render(SpriteBatch sb) { this.currentState.render(sb); }

    @Override
    public void postRender(SpriteBatch sb) { this.currentState.postRender(sb); }

    @Override
    public void debug(ShapeRenderer sr) { this.currentState.debug(sr); }

    @Override
    public void dispose() { this.currentState.dispose(); }

}