package com.github.jotask.tusk.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.menu.Menu;
import com.github.jotask.tusk.options.Options;
import com.github.jotask.tusk.play.game.Mutiplayer;
import com.github.jotask.tusk.play.game.SinglePlayer;
import com.github.jotask.tusk.splash.Splash;
import com.github.jotask.tusk.util.Constants;

public final class GameStateManager{

    private AbstractState state;

    private final Tusk tusk;

    public GameStateManager(final Tusk tusk) {
        this.tusk = tusk;
        changeState(Constants.DEFAULT_STATE);
    }

    public void update(){
        state.preUpdate();
        state.update();
        state.postUpdate();
    }

    public void render(SpriteBatch sb){
        sb.begin();
        sb.setProjectionMatrix(state.getCamera().combined);
        state.preRender(sb);
        sb.end();
        sb.begin();
        state.render(sb);
        sb.end();
        sb.begin();
        state.postRender(sb);
        sb.end();
    }

    public void debug(ShapeRenderer sr){
        sr.begin();
        sr.setProjectionMatrix(state.getCamera().combined);
        state.debug(sr);
        sr.end();
    }

    public void changeState(STATE state){
        AbstractState screen = null;
        switch (state){
            case OPTIONS:
                screen = new Options(tusk);
                break;
            case MENU:
                screen = new Menu(tusk);
                break;
            case SINGLEPLAYER:
                screen = new SinglePlayer(tusk);
                break;
            case MULTIPLAYER:
                screen = new Mutiplayer(tusk);
                break;
            case EXIT:
                screen = new Exit(tusk);
                break;
            default:
            case SPLASH:
                screen = new Splash(tusk);
                break;
        }

        if(this.state != null)
            this.state.dispose();

        this.state = screen;

        this.state.init();
    }

    public AbstractState getState(){
        return state;
    }

    public void dispose(){
        if(state != null) state.dispose();
    }

}
