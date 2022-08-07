package com.mygdx.game.base;

public abstract class Generator {
    protected long currencyPerSecond;
    protected int amount;
    protected long basePrice;
    protected long buyPrice;
    protected long amountToUnlock;

    public Generator(long currencyPerSecond, int amount, long price, long amountToUnlock) {
        this.currencyPerSecond = currencyPerSecond;
        this.amount = amount;
        this.buyPrice = price;
        this.basePrice = price;
        this.amountToUnlock = amountToUnlock;
    }

    public long generate(Stock stock) {
        return currencyPerSecond * amount;
    }

    public void boost() {
        currencyPerSecond *= 2;
    }

    public void buy(Stock stock) {
        if (buyPrice <= stock.currencyInStock) {
            stock.charge(buyPrice);
            amount++;
            buyPrice = (long) Math.ceil(basePrice * Math.pow(1.15, amount));
        }
    }

    public long getBuyPrice() {
        return buyPrice;
    }

    public long getAmountToUnlock() {
        return amountToUnlock;
    }
}
