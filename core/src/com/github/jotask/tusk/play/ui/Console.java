package com.github.jotask.tusk.play.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Console
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Console extends Table {

    private final HUD HUD;

    private TextButton b;

    public Console(HUD HUD) {
        this.HUD = HUD;

        b = new TextButton("console", HUD.getSkin(), "default");

        this.add(b);
    }

    public void update(){

    }

}
