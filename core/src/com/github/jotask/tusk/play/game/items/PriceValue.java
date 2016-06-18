package com.github.jotask.tusk.play.game.items;

/**
 * PriceValue
 *
 * @author Jose Vives Iznardo
 * @since 05/06/2016
 */
public class PriceValue {

    private float gold;
    private float silver;
    private float copper;

    public PriceValue(float gold, float silver, float copper) {
        this.gold = gold;
        this.silver = silver;
        this.copper = copper;
    }

    public float getGold() { return gold; }
    public float getSilver() { return silver; }
    public float getCopper() { return copper; }

}
