package com.mygdx.game.base;

public abstract class FlyingBoost {
    protected void applyBoost(Stock stock) {
        stock.setBoosted(true);
    }

    protected void removeBoost(Stock stock) {
        stock.setBoosted(false);
    }
}
