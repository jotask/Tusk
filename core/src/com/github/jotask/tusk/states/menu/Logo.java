package com.github.jotask.tusk.states.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.jotask.tusk.engine.game.AssetManager;

/**
 * Logo
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Logo {

    private TextureRegion region;
    private Vector2 position_logo;

    public Logo() {

        position_logo = new Vector2(0,0);

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.LOGO_TEXTURE);
        region = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());

    }

    public void update(){}

    public void render(SpriteBatch sb){
        sb.draw(region, position_logo.x, position_logo.y, region.getRegionWidth(), region.getRegionHeight());
    }

    public void debug(ShapeRenderer sr){
        sr.box(position_logo.x, position_logo.y, 0, region.getRegionWidth(), region.getRegionHeight(), 0);
    }

    public void dispose(){

    }

}
