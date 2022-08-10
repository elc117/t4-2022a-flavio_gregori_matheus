package com.mygdx.game.base;

public abstract class Stock {
    protected long currencyInStock;
    protected long totalCurrencyGenerated;
    protected Clickable clickable;
    protected GeneratorManager<?> generatorManager;

    public Stock() {
        currencyInStock = 0;
        totalCurrencyGenerated = 0;
    }

    public void click() {
        currencyInStock += clickable.generate(this);
        totalCurrencyGenerated += clickable.generate(this);
    }

    public void passSecond() {
        long generatedThisSecond = generatorManager.generate(this);
        currencyInStock += generatedThisSecond;
        totalCurrencyGenerated += generatedThisSecond;
        generatorManager.updateUnlockedGenerators(totalCurrencyGenerated, currencyInStock);
    }

    public void setClicable(Clickable clickable) {
        this.clickable = clickable;
    }

    public Clickable getClickable() {
        return clickable;
    }

    public void setGeneratorManager(GeneratorManager<?> generatorManager) {
        this.generatorManager = generatorManager;
    }

    public long getCurrencyInStock() {
        return currencyInStock;
    }

    public void setCurrencyInStock(long currencyInStock) {
        this.currencyInStock = currencyInStock;
    }

    public long getTotalCurrencyGenerated() {
        return totalCurrencyGenerated;
    }

    public void charge(long amount) {
        currencyInStock -= amount;
    }
}
