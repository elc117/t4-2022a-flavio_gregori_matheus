package com.mygdx.game.base;

public abstract class Stock {
    protected long currencyInStock;
    protected long totalCurrencyGenerated;
    protected Clicable clicable;
    protected GeneratorManager<?> generatorManager;

    public Stock(){
        currencyInStock = 0;
        totalCurrencyGenerated = 0;
    }

    public void click() {
        currencyInStock += clicable.generate(this);
        totalCurrencyGenerated += clicable.generate(this);
    }

    public void passSecond() {
        long generatedThisSecond = generatorManager.generate(this);
        currencyInStock += generatedThisSecond;
        totalCurrencyGenerated += generatedThisSecond;
        generatorManager.updateUnlockedGenerators(totalCurrencyGenerated, currencyInStock);
    }

    public void setClicable(Clicable clicable) {
        this.clicable = clicable;
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

    public void charge(long amount){
        currencyInStock -= amount;
    }
}
