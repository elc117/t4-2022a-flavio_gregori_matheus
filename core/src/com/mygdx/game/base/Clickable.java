package com.mygdx.game.base;

public abstract class Clickable {
    protected long currencyPerClick;
    protected boolean isBoosted;

    public void setBoosted(boolean isBoosted) {
        this.isBoosted = isBoosted;
    }

    public Clickable(long currencyPerClick) {
        this.currencyPerClick = currencyPerClick;
    }

    public long generate(Stock stock) {
        if (isBoosted) {
            return currencyPerClick * 2;
        } else {
            return currencyPerClick;
        }
    }

    public void boost() {
        currencyPerClick *= 2;
    }

}
