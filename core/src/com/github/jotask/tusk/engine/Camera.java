package com.github.jotask.tusk.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.states.play.entities.BodyEntity;
import com.github.jotask.tusk.states.play.entities.player.DesktopPlayerController;

public class Camera extends OrthographicCamera {

    private final float lerp = 0.1f;
    private float speedZoom = 0.005f;

    private final DesktopPlayerController controller;

    private Rectangle bounds;
    private Rectangle rect;

    public Camera(float viewportWidth, float viewportHeight) {
        super(viewportWidth, viewportHeight);
        controller = new DesktopPlayerController();
    }

    public void input(){

        float multiplier = 1f;
//        float speed = 1f;
//
//        if(controller.left()){
//            this.position.x -= speed * multiplier;
//        }
//
//        if(controller.right()){
//            this.position.x += speed * multiplier;
//        }
//
//        if(controller.up()){
//            this.position.y += speed * multiplier;
//        }
//
//        if(controller.down()){
//            this.position.y -= speed * multiplier;
//        }

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

        float s = 1f;
        this.rect = new Rectangle(this.position.x - (s / 2f), this.position.y - (s / 2f), s, s);
        float size = 4f;
        this.bounds = new Rectangle(this.position.x - (size / 2f), this.position.y - (size / 2f), size, size);

        this.update();

    }

    public void follow(BodyEntity bodyEntity){
        input();
        Vector2 v = bodyEntity.getPosition();
        this.position.x += (v.x - position.x) * lerp;
        this.position.y += (v.y - position.y) * lerp;
        this.update();
    }

    public void debug(ShapeRenderer sr){
    }

    private void debug(ShapeRenderer sr, Rectangle rect){
        sr.box(rect.x, rect.y, 0, rect.width, rect.height, 0);
    }

}
