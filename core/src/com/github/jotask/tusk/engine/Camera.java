package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.util.Util;

public class Camera extends OrthographicCamera {

    private float speedZoom = 0.005f;

    public Camera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
    }

    public void input(){

        float multiplier = 1f;

        if(Gdx.input.isKeyPressed(Input.Keys.M)){
            multiplier = 2f;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.Z)){
            this.zoom += speedZoom * multiplier;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C)){
            if(this.zoom - speedZoom * multiplier > 0f)
                this.zoom -= speedZoom * multiplier;
        }
    }

    public void follow(BodyEntity bodyEntity){
        input();
        Vector2 v = bodyEntity.getPosition();
        this.position.set(v, 10);
//        this.position.set(Util.Pixel.toMeter(v), 10);
        this.update();
    }

}
