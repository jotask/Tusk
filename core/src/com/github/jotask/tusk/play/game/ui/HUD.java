package com.github.jotask.tusk.play.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.play.game.Game;

/**
 * HUD
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public final class HUD {

    private final Skin skin;
    private final Stage stage;
    private final Table table;

    private final Life life;
    private final Console console;

    private final Game play;

    public HUD(Game play) {
        this.play = play;

        this.skin = AssetManager.get().getSkin();
        this.stage = new Stage();

        this.table = new Table();
        table.setFillParent(true);

        this.life = new Life(this);
        this.console = new Console(this);

        table.add(console).expandX().fillX().top();
        table.row().expand();
        table.add(life).expandX().fillX().bottom();

        stage.addActor(table);
        stage.setDebugAll(false);

    }

    public void update(){

    }

    public void render(SpriteBatch sb){
        sb.end();
        stage.draw();
        sb.begin();
    }

    public void debug(ShapeRenderer sr){

    }

    public void dispose(){
        stage.dispose();
    }

    public Skin getSkin() { return skin; }
    public Game getPlay() { return play; }


}
