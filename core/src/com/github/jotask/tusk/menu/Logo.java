package com.github.jotask.tusk.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.jotask.tusk.engine.game.AssetManager;

/**
 * Logo
 *
 * @author Jose Vives Iznardo
 * @since 28/05/2016
 */
public class Logo{

    private Image itemImage;

    private Label text;

    public Logo(Table table) {

        Texture texture = AssetManager.get().getAsset(AssetManager.ASSETS.LOGO_TEXTURE);
        this.itemImage = new Image();
//        itemImage.setPosition(10, 10);
        itemImage.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
        itemImage.setSize(texture.getWidth(), texture.getHeight());
        itemImage.setOrigin(itemImage.getWidth() / 2f, itemImage.getHeight() / 2f);
        itemImage.addAction(Actions.moveBy(10, 10, 3f));
        table.add(itemImage).padBottom(50f);
        table.row();

//        text = new Label("Tusk!", AssetManager.get().getSkin());
//        text.setFontScale(8f);
//        text.setOrigin(text.getWidth() / 2f, text.getHeight() / 2f);
//
//        table.add(text).row();

    }

    public void update(){

    }

}
