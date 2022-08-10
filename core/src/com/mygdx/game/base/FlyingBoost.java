package com.mygdx.game.base;

public abstract class FlyingBoost {
    void applyBoost(Stock stock) {
        stock.getClickable().setBoosted(true);
    }

    void removeBoost(Stock stock) {
        stock.getClickable().setBoosted(false);
    }
}
