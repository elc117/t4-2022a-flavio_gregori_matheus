package com.mygdx.game.base;

public abstract class Stock {
    protected long currencyInStock;
    protected float actualCurrencyInStock;
    protected long totalCurrencyGenerated;
    protected float actualCurrencyGenerated;
    protected Clickable clickable;
    protected GeneratorManager<?> generatorManager;

    public Stock() {
        actualCurrencyInStock = 0;
        actualCurrencyGenerated = 0;
        updateCurrency();
    }

    protected void updateCurrency() {
        currencyInStock = (long) actualCurrencyInStock;
        totalCurrencyGenerated = (long) actualCurrencyGenerated;
    }

    public void click() {
        long generated = clickable.generate(this);
        actualCurrencyInStock += generated;
        actualCurrencyGenerated += generated;
        updateCurrency();
    }

    public void passTime(float deltaTime) {
        long generatedInASecond = generatorManager.generate(this);
        float generated = (generatedInASecond * deltaTime);
        actualCurrencyGenerated += generated;
        actualCurrencyInStock += generated;
        updateCurrency();
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

    public long getTotalCurrencyGenerated() {
        return totalCurrencyGenerated;
    }

    public void charge(long amount) {
        actualCurrencyInStock -= amount;
        updateCurrency();
    }
}
