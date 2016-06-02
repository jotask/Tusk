package com.github.jotask.tusk.engine.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AssetManager {

    public enum ASSETS{

        PLAYER_TEXTURE("characters.png", Texture.class),
        BULLET_TEXTURE("characters.png", Texture.class),
        LOGO_TEXTURE("logo.png", Texture.class);

        String texture;
        Class<?> tClass;

        ASSETS(String texture, Class<?> tClass) {
            this.texture = texture;
            this.tClass = tClass;
        }

    }

    private BitmapFont font;
    private Skin skin;

    private com.badlogic.gdx.assets.AssetManager manager;

    private static AssetManager instance;

    public static AssetManager get(){
        if(instance == null)
            instance = new AssetManager();
        return instance;
    }

    private AssetManager() {
        this.manager = new com.badlogic.gdx.assets.AssetManager();
        this.load();
    }

    private void load(){
        this.font = new BitmapFont();
        for(ASSETS a: ASSETS.values()) this.manager.load(a.texture, a.tClass);
        manager.finishLoading();
        this.skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
    }

    public BitmapFont getFont(){ return font; }
    public Texture getAsset(ASSETS asset){
        return manager.get(asset.texture);
    }

    public Skin getSkin(){ return this.skin; }

    public void dispose(){
        manager.dispose();
        font.dispose();
        skin.dispose();
        instance = null;
    }



}
