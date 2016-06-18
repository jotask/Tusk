package com.github.jotask.tusk.states;

import com.badlogic.gdx.Gdx;
import com.github.jotask.tusk.Tusk;

/**
 * Exit
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Exit extends AbstractState {

    public Exit(final Tusk tusk) { super(tusk); }

    @Override
    public void init() {
        super.init();
        Gdx.app.exit();
    }
}
