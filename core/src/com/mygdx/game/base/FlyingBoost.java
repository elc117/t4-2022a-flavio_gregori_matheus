package com.mygdx.game.base;

public abstract class FlyingBoost {
    protected boolean isBoosting = false;
    protected float boostAmount;

    public FlyingBoost(float boostAmount) {
        this.boostAmount = boostAmount;
    }

    protected void applyBoost(Stock stock) {
        if (!isBoosting) {
            stock.addBoost(boostAmount);
            isBoosting = true;
        }
    }

    protected void removeBoost(Stock stock) {
        if (isBoosting) {
            stock.removeBoost(boostAmount);
            isBoosting = false;
        }
    }
}
