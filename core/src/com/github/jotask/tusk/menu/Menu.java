package com.github.jotask.tusk.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.tusk.engine.game.AssetManager;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.Camera;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.states.STATE;

public class Menu extends AbstractState {

    public enum BUTTONS {
        SINGLEPLAYER("SinglePlayer"),
        MULTIPLAYER("Multiplayer"),
        OPTIONS("Options"),
        EXIT("Exit");

        String text;

        BUTTONS(String s){ text = s; }

    }

    private Skin skin;
    private Stage stage;

    private Logo logo;

    @Override
    public void init() {
        this.camera = new Camera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        super.setBgColor(Color.CORAL);
        this.skin = AssetManager.get().getSkin();
        this.stage = new Stage();

        float y = Gdx.graphics.getHeight()/2 - 10f;

        for(BUTTONS type: BUTTONS.values()){
            final TextButton b = createButton(type, y -= 25f);

            if(type == BUTTONS.SINGLEPLAYER){
                b.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameStateManager.get().changeState(STATE.SINGLEPLAYER);
                    }
                });
            }else if(type == BUTTONS.MULTIPLAYER){
                b.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameStateManager.get().changeState(STATE.MULTIPLAYER);
                    }
                });
            }else if(type == BUTTONS.OPTIONS){
                b.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameStateManager.get().changeState(STATE.OPTIONS);
                    }
                });
            }else if(type == BUTTONS.EXIT){
                b.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameStateManager.get().changeState(STATE.EXIT);
                    }
                });
            }

        }

        this.logo = new Logo();

        Gdx.input.setInputProcessor(stage);

    }

    public TextButton createButton(BUTTONS b, float y){
        final TextButton button = new TextButton(b.text, skin, "default");
        button.setWidth(200f);
        button.setHeight(20f);
        button.setPosition(Gdx.graphics.getWidth() /2 - 100f, y);
        stage.addActor(button);
        return button;
    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
        logo.render(sb);
    }

    @Override
    public void debug(ShapeRenderer sr) {
        super.debug(sr);
        logo.debug(sr);
    }

    @Override
    public void dispose() {
        super.dispose();
        logo.dispose();
    }

}
