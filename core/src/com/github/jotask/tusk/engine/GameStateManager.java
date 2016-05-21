package com.github.jotask.tusk.engine;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.states.menu.Menu;
import com.github.jotask.tusk.states.options.Options;
import com.github.jotask.tusk.states.play.Play;
import com.github.jotask.tusk.states.selectplayer.SelectPlayer;
import com.github.jotask.tusk.states.splash.Splash;
import com.github.jotask.tusk.test.Test;
import com.github.jotask.tusk.util.Constants;

public class GameStateManager{

    private static GameStateManager instance;

    private AbstractState state;

    private GameStateManager() {
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
        AbstractState screen;
        switch (state){
            case PLAY:
                screen = new Play();
                break;
            case OPTIONS:
                screen = new Options();
                break;
            case MENU:
                screen = new Menu();
                break;
            case SELECTPLAYER:
                screen = new SelectPlayer();
                break;
            case TEST:
                screen = new Test();
                break;
            default:
            case SPLASH:
                screen = new Splash();
                break;
        }

        if(this.state != null)
            this.state.dispose();

        this.state = screen;

        this.state.init();
    }

    public static GameStateManager get(){
        if(instance == null)
            instance = new GameStateManager();
        return instance;
    }

    public AbstractState getState(){
        return state;
    }

    public void dispose(){
        if(state != null) state.dispose();
        instance = null;
    }

}
