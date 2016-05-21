package com.github.jotask.tusk.states.play.world.environment;

import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.github.jotask.tusk.engine.game.Timer;

public class DayCycle implements Ambient{

    private float DAY_CYCLE_SECONDS = 3f;

    public enum LIGHT{
        DAY(Color.WHITE, 1f),
        NIGHT(Color.BLUE, 1f);

        public final Color color;
        public final float alpha;

        LIGHT(Color color, float alpha) {
            this.color = color;
            this.alpha = alpha;
        }
    }

    public boolean isDay;

    private Timer timer;

    private Light light;

    public DayCycle(RayHandler rayHandler) {
        this.light = new PointLight(rayHandler, 10);
        this.light.setXray(true);
        this.light.setDistance(100f);
        this.changeLightType(LIGHT.NIGHT);
        this.timer = new Timer(DAY_CYCLE_SECONDS);
        this.isDay = true;
    }

    @Override
    public void update() {
        if(timer.isFinished()){
            if(isDay){
                changeLightType(LIGHT.NIGHT);
            }else{
                changeLightType(LIGHT.DAY);
            }
            isDay = !isDay;
        }
    }

    private void changeLightType(LIGHT l){
        light.setColor(l.color.r, l.color.g, l.color.b, l.alpha);
    }

    @Override
    public void dispose() {

    }
}
