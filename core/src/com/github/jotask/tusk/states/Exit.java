package com.github.jotask.tusk.states;

import com.badlogic.gdx.Gdx;

/**
 * Exit
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Exit extends AbstractState {

    @Override
    public void init() {
        super.init();
        Gdx.app.exit();
    }
}
