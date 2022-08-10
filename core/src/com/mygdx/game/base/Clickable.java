package com.mygdx.game.base;

public abstract class Clickable {
    protected long currencyPerClick;

    public Clickable(long currencyPerClick) {
        this.currencyPerClick = currencyPerClick;
    }

    public long generate(Stock stock) {
        return currencyPerClick;
    }

    public void boost() {
        currencyPerClick *= 2;
    }
}
