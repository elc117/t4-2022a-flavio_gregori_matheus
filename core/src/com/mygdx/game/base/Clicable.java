package com.mygdx.game.base;

public abstract class Clicable {
    protected long currencyPerClick;

    public Clicable(long currencyPerClick) {
        this.currencyPerClick = currencyPerClick;
    }

    public long generate(Stock stock) {
        return currencyPerClick;
    }

    public void boost() {
        currencyPerClick *= 2;
    }
}
