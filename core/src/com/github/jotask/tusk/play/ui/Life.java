package com.github.jotask.tusk.play.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Life
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Life extends Table{

    private final HUD hud;

    private TextButton b;

    public Life(HUD hud) {
        this.hud = hud;

        b = new TextButton("life", hud.getSkin(), "default");

        this.add(b);

    }

    public void update(){

    }

}
