package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.mygdx.game.base.Generator;
import com.mygdx.game.input.BuyButton;

public class HeadGenerator extends Generator {
    private String name;
    private BuyButton buyButton;
    private long amountToUnlock;

    public HeadGenerator(long headsPerSecond, int amount, long price, String genName, long amountUnlock, String texturePath) {
        super(headsPerSecond, amount, price);
        name = genName;
        buyButton = new BuyButton(this, texturePath);
        amountToUnlock = amountUnlock;
    }
    
    public BuyButton getButton(){
        return buyButton;
    }

    public long getAmountToUnlock(){
        return amountToUnlock;
    }

    public String getName(){
        return name;
    }
}
