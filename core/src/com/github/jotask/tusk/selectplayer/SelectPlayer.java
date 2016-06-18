package com.github.jotask.tusk.selectplayer;

import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.states.AbstractState;

public class SelectPlayer extends AbstractState {

    protected SelectPlayer(Tusk tusk) { super(tusk); }

    public enum Players {

        DEFAULT(1f, 15f);

        public final float width, height;

        Players(float width, float height) {
            this.height = height;
            this.width = width;
        }

    }

}
