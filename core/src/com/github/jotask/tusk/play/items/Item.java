package com.github.jotask.tusk.play.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.jotask.tusk.play.entities.Entity;

/**
 * Item
 *
 * @author Jose Vives Iznardo
 * @since 29/05/2016
 */
public class Item extends Entity{

    private PriceValue value;

    private final int max_quantity;
    private int quantity;

    public Item(PriceValue value) {
        this(value, 1);
    }

    public Item(PriceValue value, int max_quantity) {
        this.value = value;
        this.max_quantity = max_quantity;
        this.quantity = 1;
    }

    public void stack(Item item){
        if(max_quantity == 1) return;
        // TODO improve to short or bytes
        if(this.quantity + item.quantity < max_quantity){
            this.quantity = (this.quantity + item.quantity);
            item.quantity = 0;
        }else if(this.quantity + item.quantity > max_quantity){
            int difference = max_quantity - this.quantity;
            quantity = quantity + difference;
            item.quantity = item.quantity - difference;
        }
    }

    @Override
    public boolean toDestroy() {
        return false;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void debug(ShapeRenderer sr) {

    }

    @Override
    public void dispose() {

    }

}
