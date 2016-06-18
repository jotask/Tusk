package com.github.jotask.tusk.engine.loader;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;

/**
 * LoadState
 *
 * @author Jose Vives Iznardo
 * @since 17/06/2016
 */
public class LoadState extends AbstractState {

    private LoadingBar bar;

    private BitmapFont font;

    private AbstractState stateToLoaded;

    private float progress;

    public LoadState(final Tusk tusk, AbstractState s) {
        super(tusk);
        this.stateToLoaded = s;
        this.font = AssetManager.get().getFont();
    }

    @Override
    public void init() {
        bar = new LoadingBar(stateToLoaded.getCamera());
    }

    @Override
    public void update() {

//        this.progress = loader.manager.getProgress();

        this.progress = 1f;

        bar.update(this.progress);

        if(this.progress >= 1f){
            tusk.getGsm().finishLoading(stateToLoaded);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        this.font.draw(sb, "Loading...", 100, 300);
    }

    public void debug(ShapeRenderer sr){
        this.bar.render(sr);
    }
}
