package com.github.jotask.tusk.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.tusk.Tusk;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.states.STATE;
import com.github.jotask.tusk.util.Util;

public class Menu extends AbstractState {

    public enum BUTTONS {
        SINGLEPLAYER("SinglePlayer"),
        MULTIPLAYER("Multiplayer"),
        OPTIONS("Options"),
        EXIT("Exit");

        final String text;

        BUTTONS(final String s){ text = s; }

    }

    private Skin skin;
    private Stage stage;

    private Logo logo;

    public Menu(final Tusk tusk) { super(tusk); }

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth() / 4f, Gdx.graphics.getHeight() / 4f);
        this.setBgColor(Util.getColorFromHex(255,166,158, 1f));
        this.skin = AssetManager.get().getSkin();
        this.stage = new Stage();

        float y = Gdx.graphics.getHeight()/2 - 10f;

        final Table table = new Table();
        table.setFillParent(true);

        logo = new Logo(table);

        for(BUTTONS type: BUTTONS.values()){
            final TextButton b = createButton(type, y -= 25f);
            table.add(b).padBottom(10).row();
        }


        stage.addActor(table);
        stage.setDebugAll(false);

        Gdx.input.setInputProcessor(stage);

    }

    public TextButton createButton(BUTTONS b, float y){
        final TextButton button = new TextButton(b.text, skin, "default");
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, y);

        switch (b){
            case SINGLEPLAYER:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tusk.getGsm().changeState(STATE.SINGLEPLAYER);
                    }
                });
                break;
            case MULTIPLAYER:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tusk.getGsm().changeState(STATE.MULTIPLAYER);
                    }
                });
                break;
            case OPTIONS:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tusk.getGsm().changeState(STATE.OPTIONS);
                    }
                });
                break;
            case EXIT:
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        tusk.getGsm().changeState(STATE.EXIT);
                    }
                });
                break;
        }

        return button;
    }

    @Override
    public void update() {
        logo.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
    }

}
