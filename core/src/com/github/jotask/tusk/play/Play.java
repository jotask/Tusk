package com.github.jotask.tusk.play;

import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.states.AbstractState;

/**
 * Play
 *
 * @author Jose Vives Iznardo
 * @since 18/06/2016
 */
public final class Play extends AbstractState {

    private GameManager manager;

    public Play(final Tusk tusk) {
        super(tusk);
    }

    @Override
    public void init() {
        this.manager = new GameManager(this);
        this.setBgColor(Color.BLACK);
    }

}
