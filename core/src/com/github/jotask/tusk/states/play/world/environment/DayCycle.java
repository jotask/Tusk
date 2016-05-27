package com.github.jotask.tusk.states.play.world.environment;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.engine.game.Timer;

public class DayCycle implements Ambient{

    private float DAY_CYCLE_SECONDS = 3f;

    private RayHandler rayHandler;

    public enum LIGHT{
        DAY(Color.WHITE, 1f, 1f),
        NIGHT(Color.BLUE, 1f, 0.5f);

        public final float r;
        public final float g;
        public final float b;
        public final float a;

        public final float intensity;


        LIGHT(Color color, float intensity) {
            this(color, color.a, intensity);
        }

        LIGHT(Color color, float alpha, float intensity) {
            this(color.r, color.g, color.b, alpha, intensity);
        }

        LIGHT(float r, float g, float b, float alpha, float intensity) {
            this.r = r;
            this.g = g;
            this.b = b;
            this.a = alpha;
            this.intensity = intensity;
        }
    }

    public boolean isDay;

    private Timer timer;

    public DayCycle(RayHandler rayHandler) {
        this.rayHandler = rayHandler;
        this.changeLightType(LIGHT.NIGHT);
        this.timer = new Timer(DAY_CYCLE_SECONDS);
        this.isDay = true;
    }

    @Override
    public void update() {

        System.out.println();

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
//            if (timer.isFinished()) {
                if (isDay) {
                    changeLightType(LIGHT.NIGHT);
                } else {
                    changeLightType(LIGHT.DAY);
                }
                isDay = !isDay;
//            }
        }
    }

    private void changeLightType(LIGHT l){
        this.rayHandler.setAmbientLight(l.r, l.g, l.b, l.a);
        this.rayHandler.setAmbientLight(l.intensity);
    }

    @Override
    public void dispose() {

    }

}
