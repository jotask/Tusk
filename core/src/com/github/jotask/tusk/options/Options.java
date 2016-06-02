package com.github.jotask.tusk.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.jotask.tusk.states.AbstractState;
import com.github.jotask.tusk.states.GameStateManager;
import com.github.jotask.tusk.states.STATE;

public class Options extends AbstractState {

    public enum BUTTONS {
        BACK("Back to menu");

        String text;

        BUTTONS(String s) {
            text = s;
        }

    }

    private Skin skin;
    private Stage stage;

    @Override
    public void init() {
        super.init();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        stage = new Stage();

        float y = Gdx.graphics.getHeight() / 2 - 10f;

        for (Options.BUTTONS b : Options.BUTTONS.values()) {
            final TextButton button = new TextButton(b.text, skin, "default");
            button.setWidth(200f);
            button.setHeight(20f);
            button.setPosition(Gdx.graphics.getWidth() / 2 - 100f, y -= 25f);

            if(b == BUTTONS.BACK){
                button.addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        GameStateManager.get().changeState(STATE.MENU);
                    }
                });
            }

            stage.addActor(button);
        }

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(SpriteBatch sb) {
        stage.draw();
    }

}
