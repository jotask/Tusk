package com.github.jotask.tusk.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Util {

    public static long secondsToNano(float seconds){
        return (long)(seconds * 1000000000l);
    }

    public static class Pixel {

        public static float toMeter(float pixels) { return pixels / Constants.PPM; }

        public static Vector2 toMeter(Vector2 vecPixel) {
            return new Vector2(Pixel.toMeter(vecPixel.x), Pixel.toMeter(vecPixel.y));
        }
    }

    public static class Meter {

        public static float toPixel(float meter) { return meter * Constants.PPM; }

        public static Vector2 toPixel(Vector2 vecMeter){
            return new Vector2(Meter.toPixel(vecMeter.x), Meter.toPixel(vecMeter.y));
        }
    }

    public static class Render {
        public static void render(SpriteBatch sb, TextureRegion region, Body body){

            float w = Util.Pixel.toMeter(region.getRegionWidth());
            float h = Util.Pixel.toMeter(region.getRegionHeight());

            float x = body.getPosition().x - (w / 2);
            float y = body.getPosition().y - (h / 2);

            sb.draw(region, x, y, w, h);
        }

        public static void debug(ShapeRenderer sr, TextureRegion region, Body body) {

            float w = Util.Pixel.toMeter(region.getRegionWidth());
            float h = Util.Pixel.toMeter(region.getRegionHeight());

            float x = body.getPosition().x - (w / 2);
            float y = body.getPosition().y - (h / 2);

            sr.box(x, y, 0, w, h, 0);

        }

        public static void debug(ShapeRenderer sr, Rectangle rectangle){
            sr.box(rectangle.x, rectangle.y, 0, rectangle.width, rectangle.height, 0);
        }

    }

}
