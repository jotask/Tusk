package com.github.jotask.tusk.engine.game;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager {

    public enum ASSETS{

        PLAYER_TEXTURE("characters.png", Texture.class);

        String texture;
        Class<?> tClass;

        ASSETS(String texture, Class<?> tClass) {
            this.texture = texture;
            this.tClass = tClass;
        }
    }

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
        manager.load("characters.png", Texture.class);
        manager.finishLoading();
    }

    public Texture getAsset(ASSETS asset){
        return manager.get(asset.texture);
    }

    public void dispose(){
        manager.dispose();
    }



}
