package com.github.jotask.tusk.engine.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class DesktopPlayerController implements PlayerController{

    @Override
    public boolean left() {
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    @Override
    public boolean right() {
        return Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    @Override
    public boolean up() {
        return Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    @Override
    public boolean down() {
        return Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    @Override
    public boolean light() { return Gdx.input.isKeyJustPressed(Input.Keys.F); }

    @Override
    public boolean shoot() {
        return Gdx.input.isKeyPressed(Input.Keys.ENTER) || Gdx.input.isButtonPressed(Input.Buttons.LEFT);
    }
}
