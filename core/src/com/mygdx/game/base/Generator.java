package com.mygdx.game.base;

public abstract class Generator {
    protected long currencyPerSecond;
    protected int amount;
    protected long buyPrice;

    public Generator(long currencyPerSecond, int amount, long price) {
        this.currencyPerSecond = currencyPerSecond;
        this.amount = amount;
        this.buyPrice = price;
    }

    public long generate(Stock stock) {
        return currencyPerSecond * amount;
    }

    public void boost() {
        currencyPerSecond *= 2;
    }

    public void buy() {
        amount++;
        buyPrice++;
    }

    public long getBuyPrice(){
        return buyPrice;
    }
}
